package com.ms.usermanagement.modules.student.repository;

import com.ms.usermanagement.modules.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>,
        JpaSpecificationExecutor<Student> {
    Student findByNameIgnoreCase(String name);
}
