package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance,Integer> {

    // Custom query to search attendance records by date
    @Query(value = "SELECT * FROM Attendance WHERE date=?1",nativeQuery = true)
    List<Attendance> searchAttendanceByDate(Date date);

    @Query(value = "SELECT COUNT(attendance_id) as attendance_count FROM attendance WHERE DATE_FORMAT(date, '%Y-%m') = ?1 AND emp_id=?2", nativeQuery = true)
    int getAttendanceCount(String date,String empId);
}
