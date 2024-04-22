package com.example.SmartApparel.hrmanage.repository;

import com.example.SmartApparel.hrmanage.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

// Repository interface for Attendance entity, extends JpaRepository
public interface AttendanceRepo extends JpaRepository<Attendance,Integer> {
    // Custom query to search attendance records by date
    @Query(value = "SELECT * FROM Attendance WHERE date=?1",nativeQuery = true)
    List<Attendance> searchAttendanceByDate(Date date);
}
