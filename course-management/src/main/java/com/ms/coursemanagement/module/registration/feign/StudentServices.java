package com.ms.coursemanagement.module.registration.feign;

import com.ms.coursemanagement.base.responses.SingleRecordResp;
import com.ms.coursemanagement.module.registration.dto.feign.StudentCreate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("user-management")
public interface StudentServices {
    @PostMapping("/user-management/student")
    SingleRecordResp createStudent(StudentCreate studentCreate);
}
