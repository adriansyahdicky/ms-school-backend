package com.ms.coursemanagement.module.registration.repository;

import com.ms.coursemanagement.module.registration.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, String> {
}
