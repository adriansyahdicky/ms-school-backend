package com.ms.usermanagement.modules.teacher.dto;

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
public class TeacherCreate {
    @NotEmpty(message = "error.notEmpty")
    private String name;
    @NotEmpty(message = "error.notEmpty")
    private String address;
    @NotEmpty(message = "error.notEmpty")
    private String phoneNumber;
    @NotNull(message = "error.start.date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/jakarta")
    private LocalDate birthDate;
    @NotEmpty(message = "error.notEmpty")
    private String placeBirth;
}
