package com.ProjectFinal.SpringAssignment.repository;

import com.ProjectFinal.SpringAssignment.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
