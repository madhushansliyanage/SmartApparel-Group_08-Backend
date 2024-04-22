package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.ExpenseDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.service.ExpenseService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expense")
@CrossOrigin
public class ExpenseController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private ExpenseService expenseService;

    @PostMapping(value = "/saveExpense")
    public ResponseEntity saveExpense(@RequestBody ExpenseDTO expenseDTO){
        try {
            String response = expenseService.saveExpense(expenseDTO);
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(expenseDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Already Registered.");
                responseDTO.setContent(expenseDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (value = "/updateExpense")
    public ResponseEntity updateExpense(@RequestBody ExpenseDTO expenseDTO){
        try {
            String response = expenseService.updateExpense(expenseDTO);
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(expenseDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(expenseDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (value = "/viewExpense")
    public ResponseEntity viewExpense(){
        try {
            List<ExpenseDTO> expenseDTOList = expenseService.viewExpense();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Saved Successfully.");
            responseDTO.setContent(expenseDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (value = "/searchExpense/{ExpenseId}")
    public ResponseEntity searchExpense(@PathVariable int ExpenseId){
        try {
            ExpenseDTO expenseDTO = expenseService.searchExpense(ExpenseId);
            if (expenseDTO != null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successful.");
                responseDTO.setContent(expenseDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No expense available for this ExpenseId.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping (value = "/deleteExpense/{ExpenseId}")
    public ResponseEntity deleteExpense(@PathVariable int ExpenseId){
        try {
            String reponse = expenseService.deleteExpense(ExpenseId);
            if (reponse.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successful.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No expense available for this ExpenseId.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


