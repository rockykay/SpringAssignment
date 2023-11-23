package com.ProjectFinal.SpringAssignment.service;

import com.ProjectFinal.SpringAssignment.Dao.StudentDao;
import com.ProjectFinal.SpringAssignment.model.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentDao studentDao);
    void deleteStudent(String regNo);
    List<Student> getAllStudents();

    List<Student> getStudentsBySemester(String semesterName);

    void updateFirstName(String regNo, String newFirstName);
}
