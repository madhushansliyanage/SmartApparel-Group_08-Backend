package com.example.SmartApparel.hrmanage.service;

import com.example.SmartApparel.hrmanage.dto.AttendanceDTO;
import com.example.SmartApparel.hrmanage.entity.Attendance;
import com.example.SmartApparel.hrmanage.repository.AttendanceRepo;
import com.example.SmartApparel.hrmanage.util.VarList;
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
    private AttendanceRepo attendanceRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Method to view all attendance records
    public List<AttendanceDTO> viewAllAttendance(){
        // Retrieve all attendance records from repository
        List<Attendance> attendanceList= attendanceRepo.findAll();
        // Map to DTOs and return
        return modelMapper.map(attendanceList,new TypeToken<List<AttendanceDTO>>(){}.getType());
    }

    // Method to search attendance records by date
    public List<AttendanceDTO> searchAttendanceByDate(Date date){
        // Retrieve attendance records for the specified date
        List<Attendance> attendanceList=attendanceRepo.searchAttendanceByDate(date);
        // Map to DTOs and return
        return modelMapper.map(attendanceList,new TypeToken<List<AttendanceDTO>>(){}.getType());
    }

    // Method to add new attendance record
    public String addNewAttendance(AttendanceDTO attendanceDTO){
        // Check if attendance record already exists
        if(attendanceRepo.existsById(attendanceDTO.getAttendanceId())){
            // Return response indicating duplication
            return VarList.RSP_DUPLICATED;
        }
        else{
            // Save new attendance record to repository
            attendanceRepo.save(modelMapper.map(attendanceDTO,Attendance.class));
            // Return success response
            return VarList.RSP_SUCCESS;
        }
    }

    // Method to update an existing attendance record
    public String updateAttendance(AttendanceDTO attendanceDTO){
        // Check if attendance record exists
        if(attendanceRepo.existsById(attendanceDTO.getAttendanceId())){
            // Update attendance record in repository
            attendanceRepo.save(modelMapper.map(attendanceDTO,Attendance.class));
            // Return success response
            return VarList.RSP_SUCCESS;
        }
        else{
            // Return response indicating attendance record not found
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Method to delete an attendance record by ID
    public String deleteAttendanceByID(int attendanceId){
        // Check if attendance record exists
        if(attendanceRepo.existsById(attendanceId)){
            // Delete attendance record from repository
            attendanceRepo.deleteById(attendanceId);
            // Return success response
            return VarList.RSP_SUCCESS;
        }
        else{
            // Return response indicating attendance record not found
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
