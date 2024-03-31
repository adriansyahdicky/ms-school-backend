package com.ms.coursemanagement.module.registration.controller;

import com.ms.coursemanagement.base.responses.SingleRecordResp;
import com.ms.coursemanagement.module.registration.dto.SalaryCreate;
import com.ms.coursemanagement.module.registration.services.SalaryServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryServices salaryServices;

    @PostMapping
    public ResponseEntity<SingleRecordResp> save(@Valid @RequestBody SalaryCreate dto) {
        return ResponseEntity.ok(salaryServices.save(dto));
    }
}
