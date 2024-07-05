package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.dto.SalaryParameterDTO;
import com.example.SmartApparel.Operations.service.SalaryParameterService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing salary parameters.
 */
@RestController
@CrossOrigin
@RequestMapping("/salary-params")
public class SalaryParameterController {

    @Autowired
    private SalaryParameterService salaryParameterService;
    @Autowired
    private ResponseDTO responseDTO;

    //Endpoint for viewing all salary parameters.
    @GetMapping("/view")
    public ResponseEntity viewSalaryParams() {
        try {
            // Call service layer to retrieve all salary parameters
            List<SalaryParameterDTO> salaryParameterDTOList = salaryParameterService.viewSalaryParams();

            // Check if the list is empty
            if (salaryParameterDTOList.isEmpty()) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of Salary Parameters");
            } else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched all Salary Parameters");
            }
            responseDTO.setContent(salaryParameterDTOList);
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

    //Endpoint for searching a salary parameter by ID.
    @GetMapping("/search-by-id/{spId}")
    public ResponseEntity searchSalaryParamByID(@PathVariable int spId) {
        try {
            // Call service layer to search salary parameter by ID
            SalaryParameterDTO salaryParameterDTO = salaryParameterService.searchSalaryParamsByID(spId);

            // Check if salary parameter is found
            if (salaryParameterDTO == null) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Records of the Salary Parameter");
            } else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched the Salary Parameter");
            }
            responseDTO.setContent(salaryParameterDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Retrieves salary parameters based on the specified position.
    @GetMapping("/search-by-position/{position}")
    public ResponseEntity searchSalaryParamByPosition(@PathVariable String position) {
        try {
            // Call the service layer to search for salary parameters by position
            SalaryParameterDTO salaryParameterDTO = salaryParameterService.searchSalaryParamsByPosition(position);

            // Check if salary parameters are found
            if (salaryParameterDTO == null) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of the Salary Parameter");
            } else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched the Salary Parameter");
            }
            responseDTO.setContent(salaryParameterDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Endpoint for adding a new salary parameter.
    @PostMapping("/add")
    public ResponseEntity addSalaryParam(@RequestBody SalaryParameterDTO salaryParameterDTO){
        try{
            // Call service to add new salary parameter
            String response = salaryParameterService.addNewSalaryParam(salaryParameterDTO);

            // Check response from service
            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added Salary Parameter");
                responseDTO.setContent(salaryParameterDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Position is already exists");
                responseDTO.setContent(salaryParameterDTO);
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

    //Endpoint for updating a salary parameter.
    @PutMapping("/update")
    public ResponseEntity updateSalaryParam(@RequestBody SalaryParameterDTO salaryParameterDTO){
        try{
            // Call service layer to update salary parameter
            String response = salaryParameterService.updateSalaryParam(salaryParameterDTO);

            // Check response from service
            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully updated the Salary Parameter");
                responseDTO.setContent(salaryParameterDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such a Salary Parameter");
                responseDTO.setContent(salaryParameterDTO);
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
    @DeleteMapping("/delete/{spId}")
    public ResponseEntity deleteEmployeeByID(@PathVariable int spId){

        try{
            // Call service to delete employee by ID
            String response = salaryParameterService.deleteSalaryParamByID(spId);
            if (response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully deleted the Salary Parameter");
                responseDTO.setContent(spId);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such a Salary Parameter");
                responseDTO.setContent("empId: "+spId);
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
