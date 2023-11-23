package com.ProjectFinal.SpringAssignment.service;

import com.ProjectFinal.SpringAssignment.model.Teacher;
import com.ProjectFinal.SpringAssignment.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void saveTeacher(Teacher teacher) {
        // Add any business logic or validation here
        teacherRepository.save(teacher);
    }
}
