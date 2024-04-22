package com.example.SmartApparel.hrmanage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

// Entity class representing Attendance records
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {

    // Attendance ID (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;

    // Date of attendance
    private Date date;

    // Time of check-in
    private Time inTime;

    // Time of check-out
    private Time outTime;

    // Employee ID
    private String empId;
}