package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.AttendanceDTO;
import com.example.SmartApparel.Operations.entity.Attendance;
import com.example.SmartApparel.Operations.repo.AttendanceRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Service class for managing Attendance entities.
 */
@Service
@Transactional
public class AttendanceService {
    @Autowired
    private AttendanceRepo attendanceRepo; // Inject AttendanceRepo for database operations
    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper for entity-DTO mapping

    /**
     * Method to view all attendance records.
     *
     * @return List of AttendanceDTO objects.
     */
    public List<AttendanceDTO> viewAllAttendance() {
        List<Attendance> attendanceList = attendanceRepo.findAll(); // Retrieve all attendance records from the database
        // Map the list of Attendance entities to a list of AttendanceDTOs
        return modelMapper.map(attendanceList, new TypeToken<List<AttendanceDTO>>() {
        }.getType());
    }

    /**
     * Method to search for attendance records by attendance id.
     *
     * @param attendanceId The ID of the attendance record to search for.
     * @return The AttendanceDTO corresponding to the specified ID, or null if not found.
     */
    public AttendanceDTO searchAttendanceByID(int attendanceId) {
        // Check if attendance record exists
        if (attendanceRepo.existsById(attendanceId)) {
            // Retrieve attendance from repository and map to DTO
            Attendance attendance = attendanceRepo.findById(attendanceId).orElse(null);
//            System.out.println(attendance.getTimeDifferenceInHours());
            return modelMapper.map(attendance, AttendanceDTO.class);
        } else {
            // Return null if attendance record not found
            return null;
        }
    }

    /**
     * Method to search for attendance records by date.
     *
     * @param date The date of the attendance records to search for.
     * @return List of AttendanceDTO objects for the specified date.
     */
    public List<AttendanceDTO> searchAttendanceByDate(Date date) {
        List<Attendance> attendanceList = attendanceRepo.searchAttendanceByDate(date); // Search for attendance records by date
        // Map the list of Attendance entities to a list of AttendanceDTOs
        return modelMapper.map(attendanceList, new TypeToken<List<AttendanceDTO>>() {
        }.getType());
    }

    /**
     * Method to add a new attendance record.
     *
     * @param attendanceDTO The AttendanceDTO object representing the new attendance record.
     * @return A response string indicating success or failure.
     */
    public String addNewAttendance(AttendanceDTO attendanceDTO) {
        int response = attendanceRepo.countAttendanceByDateAndEmpId(attendanceDTO.getDate(), attendanceDTO.getEmpId());
        if (response == 0) {
            // Save the new attendance record
//            attendanceRepo.save(modelMapper.map(attendanceDTO, Attendance.class));
            Attendance attendance = modelMapper.map(attendanceDTO, Attendance.class);
            attendance.setDayOfWeekFromDate();
            attendanceRepo.save(attendance);
            return VarList.RSP_SUCCESS; // Return success
        } else {
            return VarList.RSP_DUPLICATED; // If attendance already exists for date,empId, return a duplicated error
        }
    }

    /**
     * Method to update an existing attendance record.
     *
     * @param attendanceDTO The AttendanceDTO object representing the updated attendance record.
     * @return A response string indicating success or failure.
     */
    public String updateAttendance(AttendanceDTO attendanceDTO) {
        if (attendanceRepo.existsById(attendanceDTO.getAttendanceId())) {
            Attendance attendance = modelMapper.map(attendanceDTO, Attendance.class);
            attendance.setDayOfWeekFromDate();
            attendanceRepo.save(attendance);
            return VarList.RSP_SUCCESS; // Return success
        } else {
            return VarList.RSP_NO_DATA_FOUND; // If attendance record not found, return no data found error
        }
    }

    /**
     * Method to delete an attendance record by ID.
     *
     * @param attendanceId The ID of the attendance record to be deleted.
     * @return A response string indicating success or failure.
     */
    public String deleteAttendanceByID(int attendanceId) {
        if (attendanceRepo.existsById(attendanceId)) {
            attendanceRepo.deleteById(attendanceId); // Delete the attendance record by ID
            return VarList.RSP_SUCCESS; // Return success
        } else {
            return VarList.RSP_NO_DATA_FOUND; // If attendance record not found, return no data found error
        }
    }
}
