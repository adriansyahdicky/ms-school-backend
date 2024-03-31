package com.ms.usermanagement.modules.teacher.repository;

import com.ms.usermanagement.modules.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String>,
        JpaSpecificationExecutor<Teacher> {
    TeacherRepository findByNameIgnoreCase(String name);
}
