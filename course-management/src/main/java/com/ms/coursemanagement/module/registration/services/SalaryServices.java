package com.ms.coursemanagement.module.registration.services;

import com.ms.coursemanagement.base.exception.BaseException;
import com.ms.coursemanagement.base.responses.SingleRecordResp;
import com.ms.coursemanagement.config.response_messages.localization_messages.TimeEnumMessagesKey;
import com.ms.coursemanagement.module.registration.dto.SalaryCreate;
import com.ms.coursemanagement.module.registration.dto.feign.TeacherResult;
import com.ms.coursemanagement.module.registration.feign.TeacherServices;
import com.ms.coursemanagement.module.registration.model.Salary;
import com.ms.coursemanagement.module.registration.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalaryServices {

    private final SalaryRepository salaryRepository;
    private final TeacherServices teacherServices;

    public SingleRecordResp save(SalaryCreate create){
        SingleRecordResp resultTeacher =
                teacherServices.getTeacherById(create.getTeacherId());

        if(Objects.equals(resultTeacher.getErrorSchema().getErrorCode(), "404")){
            throw new BaseException(TimeEnumMessagesKey.ERROR_NOT_FOUND,
                    "Teacher");
        }

        TeacherResult teacherResult =
                (TeacherResult) resultTeacher.getOutputSchema().getResult();

        salaryRepository.save(Salary.builder()
                        .salary(create.getSalary())
                        .monthlyAllowance(create.getMonthlyAllowance())
                        .teacherId(teacherResult.getId())
                        .teacherName(teacherResult.getName())
                .build());

        return SingleRecordResp.responseBuilder().
                result(create).build();
    }
}
