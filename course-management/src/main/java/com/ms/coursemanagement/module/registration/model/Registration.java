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
import java.time.LocalDate;

@Table(name = "registration")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Registration extends BaseEntity {
    private String studentName;
    private String address;
    private String phoneNumber;
    private String motherName;
    private String fatherName;
    private LocalDate birthDate;
    private String placeBirth;
    private String religion;
    private String zipCode;
    private String lastSchool;
    private Boolean isActive;
    private BigDecimal schoolFees;
}
