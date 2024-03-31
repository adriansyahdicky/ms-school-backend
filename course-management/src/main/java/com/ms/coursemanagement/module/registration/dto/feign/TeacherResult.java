package com.ms.coursemanagement.module.registration.dto.feign;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResult {
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate birthDate;
    private String placeBirth;
}
