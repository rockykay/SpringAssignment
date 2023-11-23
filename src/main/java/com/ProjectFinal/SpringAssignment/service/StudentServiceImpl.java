package com.ProjectFinal.SpringAssignment.service;

import com.ProjectFinal.SpringAssignment.Dao.StudentDao;
import com.ProjectFinal.SpringAssignment.Dao.StudentListDao;
import com.ProjectFinal.SpringAssignment.model.Semester;
import com.ProjectFinal.SpringAssignment.model.Student;
import com.ProjectFinal.SpringAssignment.repository.SemesterRepository;
import com.ProjectFinal.SpringAssignment.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, SemesterRepository semesterRepository) {
        this.studentRepository = studentRepository;
        this.semesterRepository = semesterRepository;
    }

    private boolean validateDates(StudentDao studentDao) {
        LocalDate startDate = studentDao.getStartDate();
        LocalDate endDate = studentDao.getEndDate();

        // Check if startDate is before endDate
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsBySemester(String semesterName) {
        return studentRepository.findBySemesterName(semesterName);
    }

    @Override
    public void saveStudent(StudentDao studentDao) {
        if (validateDates(studentDao)) {
            Student student = new Student();
            BeanUtils.copyProperties(studentDao, student);

            // Check if the semesterName already exists in the Semester table
            String semesterName = studentDao.getSemesterName();
            Semester existingSemester = semesterRepository.findBySemesterName(semesterName);

            if (existingSemester != null) {
                // Use the existing semesterName
                student.setSemesterName(semesterName);
            } else {
                // Create a new Semester record
                Semester newSemester = new Semester();
                newSemester.setSemesterName(semesterName);
                newSemester.setEndDate(studentDao.getEndDate());
                newSemester.setStartDate(studentDao.getStartDate());
                // Set other Semester properties if needed

                semesterRepository.save(newSemester);

                // Set the semesterName in the student entity
                student.setSemesterName(semesterName);
            }

            studentRepository.save(student);
        } else {
            // Handle validation failure (throw an exception or return an error response)
            throw new IllegalArgumentException("Invalid dates: startDate must be before endDate");
        }
    }

    @Override
    public void deleteStudent(String regNo) {
        // Find the student by regNo
        Optional<Student> optionalStudent = studentRepository.findById(regNo);

        if (optionalStudent.isPresent()) {
            // Delete the student
            studentRepository.delete(optionalStudent.get());
        } else {
            // Handle the case where the student with the given regNo is not found
            throw new NoSuchElementException("Student not found with regNo: " + regNo);
        }
    }

    @Override
    public void updateFirstName(String regNo, String newFirstName) {
        // Find the student by regNo
        Optional<Student> optionalStudent = studentRepository.findById(regNo);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            // Update the first name
            student.setFirstName(newFirstName);

            // Save the updated student
            studentRepository.save(student);
        } else {
            // Handle the case where the student with the given regNo is not found
            throw new NoSuchElementException("Student not found with regNo: " + regNo);
        }
    }


    private StudentListDao convertToStudentListDTO(Student student) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(student.getDateOfBirth(), formatter);

        // Convert LocalDate to Date
        Date dateOfBirthDate = Date.from(dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Convert LocalDateTime to Instant
        Instant creationTimestamp = student.getCreationTimestamp() != null
                ? student.getCreationTimestamp().atZone(ZoneId.systemDefault()).toInstant()
                : null;

        return new StudentListDao(
                student.getRegNo(),
                student.getFirstName(),
                dateOfBirthDate,
                creationTimestamp
        );
    }
}
