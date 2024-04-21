package com.example.SmartApparel.hrmanage.controller;

import com.example.SmartApparel.hrmanage.dto.EmployeeDTO;
import com.example.SmartApparel.hrmanage.dto.ResponseDTO;
import com.example.SmartApparel.hrmanage.service.EmployeeService;
import com.example.SmartApparel.hrmanage.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDTO responseDTO;

    // Endpoint for viewing all employees
    @GetMapping("/view")
    public ResponseEntity viewEmployees(){
        try{
            // Call service layer to retrieve all employees
            List<EmployeeDTO> employeeDTOList = employeeService.viewAllEmployees();

            // Check if list is empty
            if(employeeDTOList.isEmpty()){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of employees");
            }else{
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched all employees");
            }
            responseDTO.setContent(employeeDTOList);
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

    // Endpoint for searching an employee by ID
    @GetMapping("/search/{empId}")
    public ResponseEntity searchEmployeeByID(@PathVariable String empId){
        try{
            // Call service layer to search employee by ID
            EmployeeDTO employeeDTO = employeeService.searchEmployeeByID(empId);

            // Check if employee is found
            if (employeeDTO==null){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of the employee");
            }else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched the employee");
            }
            responseDTO.setContent(employeeDTO);
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

    // Endpoint for adding a new employee
    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            // Call service to add new employee
            String response = employeeService.addNewEmployee(employeeDTO);

            // Check response from service
            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee id already exists");
                responseDTO.setContent(employeeDTO);
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

    // Endpoint for updating an employee
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            // Call service layer to update employee
            String response = employeeService.updateEmployee(employeeDTO);

            // Check response from service
            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully updated the employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such an employee");
                responseDTO.setContent(employeeDTO);
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

    // Endpoint for deleting an employee by ID
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity deleteEmployeeByID(@PathVariable String empId){

        try{
            // Call service to delete employee by ID
            String response = employeeService.deleteEmployeeByID(empId);
            if (response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully deleted the employee");
                responseDTO.setContent(empId);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such an employee");
                responseDTO.setContent("empId: "+empId);
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
