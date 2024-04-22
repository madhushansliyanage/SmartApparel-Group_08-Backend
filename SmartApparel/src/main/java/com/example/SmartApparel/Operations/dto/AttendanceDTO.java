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
    private int attendanceId;
    private Date date;
    private Time inTime;
    private Time outTime;
    private String empId;
}
