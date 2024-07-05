package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

/**
 * Entity class representing the attendance details of an employee.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {

    // Unique identifier for the attendance record
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;

    // Date of the attendance
    private Date date;

    // Time when the employee checked in
    private Time inTime;

    // Time when the employee checked out
    private Time outTime;

    // Employee ID associated with this attendance record
    private String empId;
}
