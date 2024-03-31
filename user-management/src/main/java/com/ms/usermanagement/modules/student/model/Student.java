package com.ms.usermanagement.modules.student.model;

import com.ms.usermanagement.base.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Table(name = "student")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends BaseEntity {
    private String name;
    private String address;
    private String phoneNumber;
    private String motherName;
    private String fatherName;
    private LocalDate birthDate;
    private String placeBirth;
}
