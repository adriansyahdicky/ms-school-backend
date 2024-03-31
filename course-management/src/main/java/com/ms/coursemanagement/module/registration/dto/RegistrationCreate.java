package com.ms.coursemanagement.module.registration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationCreate {
    @NotEmpty(message = "error.notEmpty")
    private String studentName;
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
    @NotEmpty(message = "error.notEmpty")
    private String religion;
    @NotEmpty(message = "error.notEmpty")
    private String zipCode;
    @NotEmpty(message = "error.notEmpty")
    private String lastSchool;
    @NotNull(message = "error.notEmpty")
    private BigDecimal schoolFees;
}
