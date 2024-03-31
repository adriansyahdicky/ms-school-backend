package com.ms.coursemanagement.module.registration.dto.feign;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreate {
    private String name;
    private String address;
    private String phoneNumber;
    private String motherName;
    private String fatherName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/jakarta")
    private LocalDate birthDate;
    private String placeBirth;
}
