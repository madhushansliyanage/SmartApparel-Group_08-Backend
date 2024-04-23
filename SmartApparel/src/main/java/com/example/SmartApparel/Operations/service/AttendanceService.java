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

@Service
@Transactional
public class AttendanceService {
    @Autowired
    private AttendanceRepo attendanceRepo; // Inject AttendanceRepo for database operations
    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper for entity-DTO mapping

    // Method to view all attendance records
    public List<AttendanceDTO> viewAllAttendance(){
        List<Attendance> attendanceList= attendanceRepo.findAll(); // Retrieve all attendance records from the database
        // Map the list of Attendance entities to a list of AttendanceDTOs
        return modelMapper.map(attendanceList,new TypeToken<List<AttendanceDTO>>(){}.getType());
    }

    // Method to search for attendance records by date
    public List<AttendanceDTO> searchAttendanceByDate(Date date){
        List<Attendance> attendanceList=attendanceRepo.searchAttendanceByDate(date); // Search for attendance records by date
        // Map the list of Attendance entities to a list of AttendanceDTOs
        return modelMapper.map(attendanceList,new TypeToken<List<AttendanceDTO>>(){}.getType());
    }

    // Method to add a new attendance record
    public String addNewAttendance(AttendanceDTO attendanceDTO){
        if(attendanceRepo.existsById(attendanceDTO.getAttendanceId())){
            return VarList.RSP_DUPLICATED; // If attendance ID already exists, return a duplicated error
        }
        else{
            attendanceRepo.save(modelMapper.map(attendanceDTO,Attendance.class)); // Save the new attendance record
            return VarList.RSP_SUCCESS; // Return success
        }
    }

    // Method to update an existing attendance record
    public String updateAttendance(AttendanceDTO attendanceDTO){
        if(attendanceRepo.existsById(attendanceDTO.getAttendanceId())){
            attendanceRepo.save(modelMapper.map(attendanceDTO,Attendance.class)); // Update the attendance record
            return VarList.RSP_SUCCESS; // Return success
        }
        else{
            return VarList.RSP_NO_DATA_FOUND; // If attendance record not found, return no data found error
        }
    }

    // Method to delete an attendance record by ID
    public String deleteAttendanceByID(int attendanceId){
        if(attendanceRepo.existsById(attendanceId)){
            attendanceRepo.deleteById(attendanceId); // Delete the attendance record by ID
            return VarList.RSP_SUCCESS; // Return success
        }
        else{
            return VarList.RSP_NO_DATA_FOUND; // If attendance record not found, return no data found error
        }
    }
}
