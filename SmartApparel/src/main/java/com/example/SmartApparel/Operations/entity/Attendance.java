package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @Column(nullable = false)
    private Date date;

    // Time when the employee checked in
    @Column(nullable = false)
    private Time inTime;

    // Time when the employee checked out
    private Time outTime;

    // Employee ID associated with this attendance record
    @Column(nullable = false)
    private String empId;

    @Column(nullable = false)
    private String dayOfWeek;

    public void setDayOfWeekFromDate() {
        LocalDate localDate = date.toLocalDate();
//        System.out.println("LocalDate: " + localDate);
        this.dayOfWeek = localDate.getDayOfWeek().toString();
    }

    public double getTimeDifferenceInHours() {
        LocalTime start = inTime.toLocalTime();
        LocalTime end = outTime.toLocalTime();
        Duration duration = Duration.between(start, end);
//        System.out.println(duration);
//        System.out.println(duration.toMinutes());
        return duration.toMinutes() / 60.0;
    }
}
