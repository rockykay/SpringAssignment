package com.ProjectFinal.SpringAssignment.controller;

import com.ProjectFinal.SpringAssignment.Dao.StudentDao;
import com.ProjectFinal.SpringAssignment.model.Student;
import com.ProjectFinal.SpringAssignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/{regNo}/updateFirstName", method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<String> updateFirstName(@PathVariable String regNo, @RequestBody Map<String, String> requestBody) {
        String newFirstName = requestBody.get("newFirstName");
        studentService.updateFirstName(regNo, newFirstName);
        return new ResponseEntity<>("First name updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{regNo}")
    public ResponseEntity<String> deleteStudent(@PathVariable String regNo) {
        studentService.deleteStudent(regNo);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> saveStudent(@RequestBody StudentDao studentDao) {
        studentService.saveStudent(studentDao);
        return new ResponseEntity<>("Student saved successfully", HttpStatus.CREATED);
    }
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/semester/")
    public List<Student> getStudentsBySemester(
            @RequestParam(name = "semesterName") String semesterName) {
        return studentService.getStudentsBySemester(semesterName);
    }


}
