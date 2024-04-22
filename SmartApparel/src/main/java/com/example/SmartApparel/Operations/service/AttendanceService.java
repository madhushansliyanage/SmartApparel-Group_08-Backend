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
    private AttendanceRepo attendanceRepo;
    @Autowired
    private ModelMapper modelMapper;
    public List<AttendanceDTO> viewAllAttendance(){
        List<Attendance> attendanceList= attendanceRepo.findAll();
        return modelMapper.map(attendanceList,new TypeToken<List<AttendanceDTO>>(){}.getType());
    }

    public List<AttendanceDTO> searchAttendanceByDate(Date date){
        List<Attendance> attendanceList=attendanceRepo.searchAttendanceByDate(date);
        return modelMapper.map(attendanceList,new TypeToken<List<AttendanceDTO>>(){}.getType());
    }

    public String addNewAttendance(AttendanceDTO attendanceDTO){
        if(attendanceRepo.existsById(attendanceDTO.getAttendanceId())){
            return VarList.RSP_DUPLICATED;
        }
        else{
            attendanceRepo.save(modelMapper.map(attendanceDTO,Attendance.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateAttendance(AttendanceDTO attendanceDTO){
        if(attendanceRepo.existsById(attendanceDTO.getAttendanceId())){
            attendanceRepo.save(modelMapper.map(attendanceDTO,Attendance.class));
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String deleteAttendanceByID(int attendanceId){
        if(attendanceRepo.existsById(attendanceId)){
            attendanceRepo.deleteById(attendanceId);
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
