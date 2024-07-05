package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.dto.SalaryDTO;
import com.example.SmartApparel.Operations.service.SalaryService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing salary.
 */
@RestController
@RequestMapping("/salary")
@CrossOrigin
public class SalaryController {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private ResponseDTO responseDTO;

    // Endpoint for viewing all Salaries
    @GetMapping("/view")
    public ResponseEntity viewSalaries(){
        try{

            List<SalaryDTO> salaryDTOList = salaryService.viewAllSalary();

            if(salaryDTOList.isEmpty()){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of Salaries");
            }else{
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched all Salaries");
            }
            responseDTO.setContent(salaryDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for searching salary by ID
    @GetMapping("/search/{salaryId}")
    public ResponseEntity searchSalaryByID(@PathVariable int salaryId){
        try{

            SalaryDTO salaryDTO = salaryService.searchSalaryByID(salaryId);

            if (salaryDTO==null){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of the Salary");
            }else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched the Salary");
            }
            responseDTO.setContent(salaryDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for calculate salary by ID and yearMonth
    @GetMapping("/calculate/{empId}/{yearMonth}")
    public ResponseEntity calculateSalary(@PathVariable String empId,@PathVariable String yearMonth){

        try{
            String response = salaryService.calculateSalary(empId,yearMonth);

            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully Calculated Salary");
                responseDTO.setContent("empId: "+empId+"  yearNMonth: "+yearMonth);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Salary is already exist for "+empId+" for "+yearMonth);
                responseDTO.setContent("empId: "+empId+"  yearNMonth: "+yearMonth);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for calculate all salary by yearMonth
    @GetMapping("/calculate-all/{yearMonth}")
    public ResponseEntity calculateSalaryForAll(@PathVariable String yearMonth){

        try{
            String response = salaryService.calculateSalaryForAll(yearMonth);

            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully Calculated Salaries");
                responseDTO.setContent(yearMonth);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Salaries may have duplicated for "+yearMonth);
                responseDTO.setContent("yearNMonth: "+yearMonth);
                return new ResponseEntity(responseDTO,HttpStatus.CONFLICT);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(yearMonth);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for adding a salary
    @PostMapping("/add")
    public ResponseEntity addSalary(@RequestBody SalaryDTO salaryDTO){
        try{

            String response = salaryService.addNewSalary(salaryDTO);

            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added Salary");
                responseDTO.setContent(salaryDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Salary id already exists");
                responseDTO.setContent(salaryDTO);
                return new ResponseEntity(responseDTO,HttpStatus.CONFLICT);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for updating a salary
    @PutMapping("/update")
    public ResponseEntity updateSalary(@RequestBody SalaryDTO salaryDTO){
        try{

            String response = salaryService.updateSalary(salaryDTO);

            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully updated the Salary");
                responseDTO.setContent(salaryDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such a Salary");
                responseDTO.setContent(salaryDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint for delete salary by ID
    @DeleteMapping("/delete/{salaryId}")
    public ResponseEntity deleteSalaryByID(@PathVariable int salaryId){

        try{
            System.out.println("inside delete controller method");
            String response = salaryService.deleteSalaryByID(salaryId);
            if (response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully deleted the Salary");
                responseDTO.setContent(salaryId);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such a Salary");
                responseDTO.setContent("empId: "+salaryId);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
