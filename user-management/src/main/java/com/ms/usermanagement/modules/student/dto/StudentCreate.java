package com.ms.usermanagement.modules.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreate {
    @NotEmpty(message = "error.notEmpty")
    private String name;
    @NotEmpty(message = "error.notEmpty")
    private String address;
    @NotEmpty(message = "error.notEmpty")
    private String phoneNumber;
    @NotEmpty(message = "error.notEmpty")
    private String motherName;
    @NotEmpty(message = "error.notEmpty")
    private String fatherName;
    @NotNull(message = "error.start.date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/jakarta")
    private LocalDate birthDate;
    @NotEmpty(message = "error.notEmpty")
    private String placeBirth;
}
