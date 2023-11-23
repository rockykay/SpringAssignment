package com.ProjectFinal.SpringAssignment.controller;

import com.ProjectFinal.SpringAssignment.model.Teacher;
import com.ProjectFinal.SpringAssignment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<String> saveTeacher(@RequestBody Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return new ResponseEntity<>("Teacher saved successfully", HttpStatus.CREATED);
    }

}
