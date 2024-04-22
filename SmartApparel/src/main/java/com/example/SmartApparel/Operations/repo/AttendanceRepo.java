package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance,Integer> {

    @Query(value = "SELECT * FROM Attendance WHERE date=?1",nativeQuery = true)
    List<Attendance> searchAttendanceByDate(Date date);
}
