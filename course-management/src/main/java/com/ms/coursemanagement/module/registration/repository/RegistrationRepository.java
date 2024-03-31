package com.ms.coursemanagement.module.registration.repository;

import com.ms.coursemanagement.module.registration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
    Registration findByStudentNameIgnoreCase(String studentName);
}
