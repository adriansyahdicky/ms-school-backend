package com.ms.usermanagement.modules.student.controller;

import com.ms.usermanagement.base.responses.SingleRecordResp;
import com.ms.usermanagement.modules.student.dto.StudentCreate;
import com.ms.usermanagement.modules.student.services.StudentServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServices studentServices;
    @GetMapping
    public ResponseEntity<Object> view(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Map<String, Object> search,
            @RequestParam(value = "sort_field", required = false) String sortField,
            @RequestParam(value = "sort_direction", defaultValue = "asc") String sortDirection){
        return ResponseEntity.ok(studentServices.view(page,
                size,
                search,
                sortField,
                sortDirection));
    }

    @PostMapping
    public ResponseEntity<SingleRecordResp> save(@Valid @RequestBody StudentCreate dto) {
        return ResponseEntity.ok(studentServices.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleRecordResp> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentServices.findById(id));
    }

}
