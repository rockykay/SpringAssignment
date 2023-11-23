package com.ProjectFinal.SpringAssignment.repository;

import com.ProjectFinal.SpringAssignment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findBySemesterName(String semesterName);
}
