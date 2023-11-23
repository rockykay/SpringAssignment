package com.ProjectFinal.SpringAssignment.service;

import com.ProjectFinal.SpringAssignment.Dao.TeacherDao;
import com.ProjectFinal.SpringAssignment.model.Teacher;
import com.ProjectFinal.SpringAssignment.repository.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServic{

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void saveTeacher(TeacherDao teacherDao) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDao, teacher);
        // Additional logic for saving teacher...
        teacherRepository.save(teacher);
    }
}
