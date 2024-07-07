package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * Repository interface for managing Attendance entities.
 */
public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {

    /**
     * Custom query to search attendance records by date.
     *
     * @param date The date for which attendance records are searched.
     * @return A list of attendance records for the given date.
     */
    @Query(value = "SELECT * FROM Attendance WHERE date=?1", nativeQuery = true)
    List<Attendance> searchAttendanceByDate(Date date);

    /**
     * Custom query to get the count of attendance records for a specific employee ID and Date
     *
     * @param date   Date of the employee attend.
     * @param empId  The ID of the employee.
     * @return The count of attendance records for the given date and employee.
     */
    @Query(value = "SELECT COUNT(attendance_id) FROM Attendance WHERE date=?1 AND emp_id=?2",nativeQuery = true)
    int countAttendanceByDateAndEmpId(Date date, String empId);

    /**
     * Custom query to get the count of attendance records for a specific month and employee.
     *
     * @param date   The year and month for which the attendance count is calculated (in the format YYYY-MM).
     * @param empId  The ID of the employee.
     * @return The count of attendance records for the given month and employee.
     */
    @Query(value = "SELECT COUNT(attendance_id) as attendance_count FROM attendance WHERE DATE_FORMAT(date, '%Y-%m') = ?1 AND emp_id=?2", nativeQuery = true)
    int getAttendanceCount(String date, String empId);
}
