package com.ProjectFinal.SpringAssignment.Dao;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class StudentListDao {
    private String regNo;
    private String firstName;
    private Date dateOfBirth;
    private Instant creationTimestamp;
    // Constructors, getters, and setters...

    public StudentListDao(String regNo, String firstName, Date dateOfBirth, Instant creationTimestamp) {
        this.regNo = regNo;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.creationTimestamp = creationTimestamp;
    }
}
