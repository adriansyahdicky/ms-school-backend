package com.ms.coursemanagement.module.registration.model;

import com.ms.coursemanagement.base.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Table(name = "salary")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Salary extends BaseEntity {
    private String teacherId;
    private String teacherName;
    private BigDecimal salary;
    private BigDecimal monthlyAllowance;
}
