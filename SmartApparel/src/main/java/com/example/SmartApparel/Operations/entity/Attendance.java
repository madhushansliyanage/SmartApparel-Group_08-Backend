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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId; // Unique identifier for the attendance record

    private Date date; // Date of the attendance

    private Time inTime; // Time when the employee checked in

    private Time outTime; // Time when the employee checked out

    private String empId; // Employee ID associated with this attendance record
}
