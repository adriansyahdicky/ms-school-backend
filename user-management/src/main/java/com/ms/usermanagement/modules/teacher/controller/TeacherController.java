package com.ms.usermanagement.modules.teacher.controller;

import com.ms.usermanagement.base.responses.SingleRecordResp;
import com.ms.usermanagement.modules.teacher.dto.TeacherCreate;
import com.ms.usermanagement.modules.teacher.services.TeacherServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherServices teacherServices;
    @GetMapping
    public ResponseEntity<Object> view(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Map<String, Object> search,
            @RequestParam(value = "sort_field", required = false) String sortField,
            @RequestParam(value = "sort_direction", defaultValue = "asc") String sortDirection){
        return ResponseEntity.ok(teacherServices.view(page,
                size,
                search,
                sortField,
                sortDirection));
    }

    @PostMapping
    public ResponseEntity<SingleRecordResp> save(@Valid @RequestBody TeacherCreate dto) {
        return ResponseEntity.ok(teacherServices.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleRecordResp> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(teacherServices.findById(id));
    }


}
