package com.ms.coursemanagement.module.registration.services;

import com.ms.coursemanagement.base.exception.BaseException;
import com.ms.coursemanagement.base.responses.SingleRecordResp;
import com.ms.coursemanagement.config.response_messages.localization_messages.TimeEnumMessagesKey;
import com.ms.coursemanagement.module.registration.dto.RegistrationActivate;
import com.ms.coursemanagement.module.registration.dto.RegistrationCreate;
import com.ms.coursemanagement.module.registration.dto.feign.StudentCreate;
import com.ms.coursemanagement.module.registration.feign.StudentServices;
import com.ms.coursemanagement.module.registration.model.Registration;
import com.ms.coursemanagement.module.registration.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServices {

    private final RegistrationRepository registrationRepository;
    private final StudentServices studentServices;

    public SingleRecordResp save(RegistrationCreate dto) {
        if(ObjectUtils.isNotEmpty(registrationRepository.findByStudentNameIgnoreCase(dto.getStudentName()))){
            throw new BaseException(TimeEnumMessagesKey.ERROR_ALREADY_EXISTS,
                    "Registration");
        }
        return SingleRecordResp.
                responseBuilder()
                .result(registrationRepository.save(Registration.builder()
                        .studentName(dto.getStudentName())
                        .address(dto.getAddress())
                        .motherName(dto.getMotherName())
                        .fatherName(dto.getFatherName())
                        .phoneNumber(dto.getPhoneNumber())
                        .birthDate(dto.getBirthDate())
                        .placeBirth(dto.getPlaceBirth())
                                .lastSchool(dto.getLastSchool())
                                .isActive(Boolean.FALSE)
                                .religion(dto.getReligion())
                                .schoolFees(dto.getSchoolFees())
                                .zipCode(dto.getZipCode())
                        .build()))
                .build();
    }

    public SingleRecordResp activateStudent(String id, RegistrationActivate dto) {
        if(dto.getSchoolFees().compareTo(new BigDecimal(10_000_000)) < 0){
            throw new BaseException(TimeEnumMessagesKey.ERROR_ENOUGH_MONEY);
        }
        return registrationRepository.findById(id)
                .map(registration -> {
                    registration.setIsActive(Boolean.TRUE);
                    registration.setSchoolFees(dto.getSchoolFees());
                    registrationRepository.save(registration);
                    //disini kirim data ke service student
                    SingleRecordResp singleRecordResp = studentServices.createStudent(StudentCreate.builder()
                                    .name(registration.getStudentName())
                                    .placeBirth(registration.getPlaceBirth())
                                    .address(registration.getAddress())
                                    .phoneNumber(registration.getPhoneNumber())
                                    .motherName(registration.getMotherName())
                                    .fatherName(registration.getFatherName())
                                    .birthDate(registration.getBirthDate())
                            .build());
                    log.info("response student service {}", singleRecordResp.getErrorSchema().getErrorCode());
                    return SingleRecordResp.responseBuilder().result("Success activated student");
                }).orElseThrow(() -> new BaseException(TimeEnumMessagesKey.ERROR_NOT_FOUND,
                        "Registration")).build();
    }
}
