package com.ProjectFinal.SpringAssignment.service;

import com.ProjectFinal.SpringAssignment.Dao.TeacherDao;
import com.ProjectFinal.SpringAssignment.model.Teacher;

import java.util.List;

public interface TeacherServic {
    List<Teacher> getAllTeachers();
    void saveTeacher(TeacherDao teacherDao);
}
