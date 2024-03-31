package com.ms.coursemanagement.module.registration.feign;

import com.ms.coursemanagement.base.responses.SingleRecordResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("user-management")
public interface TeacherServices {
    @PostMapping("/user-management/teacher/{id}")
    SingleRecordResp getTeacherById(String id);
}
