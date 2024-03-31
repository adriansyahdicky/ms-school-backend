package com.ms.coursemanagement.module.registration.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryCreate {
    private String teacherId;
    private BigDecimal salary;
    private BigDecimal monthlyAllowance;
}
