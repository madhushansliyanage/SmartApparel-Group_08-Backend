package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDTO {
    // Attendance ID
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
