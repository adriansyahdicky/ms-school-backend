package com.ms.usermanagement.modules.teacher.model;

import com.ms.usermanagement.base.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Table(name = "teacher")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Teacher extends BaseEntity {
    private String name;
    private String address;
    private String phoneNumber;
    private LocalDate birthDate;
    private String placeBirth;
}
