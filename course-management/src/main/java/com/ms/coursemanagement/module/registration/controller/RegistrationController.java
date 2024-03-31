package com.ms.coursemanagement.module.registration.controller;

import com.ms.coursemanagement.base.responses.SingleRecordResp;
import com.ms.coursemanagement.module.registration.dto.RegistrationActivate;
import com.ms.coursemanagement.module.registration.dto.RegistrationCreate;
import com.ms.coursemanagement.module.registration.services.RegistrationServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationServices registrationServices;

    @PostMapping
    public ResponseEntity<SingleRecordResp> save(@Valid @RequestBody RegistrationCreate dto) {
        return ResponseEntity.ok(registrationServices.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SingleRecordResp> activated(
            @PathVariable("id") String id,
            @Valid @RequestBody RegistrationActivate dto) {
        return ResponseEntity.ok(registrationServices.activateStudent(id,dto));
    }

}
