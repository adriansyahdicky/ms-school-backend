package com.ms.coursemanagement.module.registration.services;

import com.ms.coursemanagement.base.exception.BaseException;
import com.ms.coursemanagement.base.responses.PagingSortingResp;
import com.ms.coursemanagement.base.responses.SingleRecordResp;
import com.ms.coursemanagement.base.searchspec.GenericSpecification;
import com.ms.coursemanagement.base.searchspec.SearchCriteria;
import com.ms.coursemanagement.base.searchspec.SearchOperation;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static com.ms.coursemanagement.module.registration.constant.RegistrationFilterConstant.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServices {

    private final RegistrationRepository registrationRepository;
    private final StudentServices studentServices;

    public PagingSortingResp view(int page,
                                  int size,
                                  Map<String, Object> search,
                                  String sort,
                                  String sortDescription){
        Sort.Direction direction = sortDescription.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        if (sort == null)
            sort = CREATED_AT;
        String field = CREATED_AT;

        if (sort.equals(NAME))
            field = NAME;
        else if (sort.equals(ADDRESS))
            field = ADDRESS;

        PageRequest pageable = PageRequest.of(page, size, Sort.by(new Sort.Order(direction, field).ignoreCase()));

        GenericSpecification<Registration> specification =
                buildSpecification(search);

        Page<Registration> pageResult=registrationRepository.findAll(specification,
                pageable);

        return PagingSortingResp.responseBuilder()
                .records(pageResult.getContent())
                .currentPage(pageResult.getNumber())
                .currentSize(pageResult.getSize())
                .totalPage(pageResult.getTotalPages())
                .totalSize(pageResult.getTotalElements())
                .build();
    }

    private GenericSpecification<Registration> buildSpecification(Map<String, Object> search) {
        GenericSpecification<Registration> genericSpecification = new GenericSpecification<>();
        search.forEach((k, v) -> {

            Optional.of(k).filter(s -> s.equalsIgnoreCase(NAME)
                    && ObjectUtils.isNotEmpty(v)).ifPresent(s ->
                    genericSpecification.add(new SearchCriteria(NAME, v, SearchOperation.MATCH)));

            Optional.of(k).filter(s -> s.equalsIgnoreCase(ADDRESS)
                    && ObjectUtils.isNotEmpty(v)).ifPresent(s ->
                    genericSpecification.add(new SearchCriteria(ADDRESS, v, SearchOperation.MATCH)));
        });
        return genericSpecification;
    }

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
