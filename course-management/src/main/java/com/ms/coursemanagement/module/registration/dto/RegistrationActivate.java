package com.ms.coursemanagement.module.registration.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationActivate {
    private BigDecimal schoolFees;
}
