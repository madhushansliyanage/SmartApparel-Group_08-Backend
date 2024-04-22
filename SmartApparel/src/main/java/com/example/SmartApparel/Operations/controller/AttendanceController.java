package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.AttendanceDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.service.AttendanceService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/view")
    public ResponseEntity viewAttendance(){
        try{
            List<AttendanceDTO> attendanceDTOList=attendanceService.viewAllAttendance();
            if (attendanceDTOList.isEmpty()){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of Attendance");
            }
            else{
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched all attendances");
            }
            responseDTO.setContent(attendanceDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{date}")
    public ResponseEntity searchAttendance(@PathVariable Date date){
        try{
            List<AttendanceDTO> attendanceDTOList = attendanceService.searchAttendanceByDate(date);

            if(attendanceDTOList==null){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of the date");
            }else{
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched the attendance details");
            }
            responseDTO.setContent(attendanceDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addAttendance(@RequestBody AttendanceDTO attendanceDTO){
        try{
            String response = attendanceService.addNewAttendance(attendanceDTO);

            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added attendance");
                responseDTO.setContent(attendanceDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("attendance id already exists");
                responseDTO.setContent(attendanceDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateAttendance(@RequestBody AttendanceDTO attendanceDTO){
        try{
            String response = attendanceService.updateAttendance(attendanceDTO);

            if (response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully updated the Attendance");
                responseDTO.setContent(attendanceDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such an Attendance details");
                responseDTO.setContent(attendanceDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{attendanceId}")
    public ResponseEntity deleteAttendance(@PathVariable int attendanceId){
        try{
            String response = attendanceService.deleteAttendanceByID(attendanceId);
            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully deleted the Attendance details");
                responseDTO.setContent(attendanceId);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such attendance details");
                responseDTO.setContent("Attendance_ID: "+attendanceId);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
