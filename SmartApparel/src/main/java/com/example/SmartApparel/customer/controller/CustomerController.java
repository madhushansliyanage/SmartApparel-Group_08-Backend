package com.example.SmartApparel.customer.controller;

import com.example.SmartApparel.customer.dto.CustomerDTO;
import com.example.SmartApparel.customer.dto.ResponseDTO;
import com.example.SmartApparel.customer.service.CustomerService;
import com.example.SmartApparel.customer.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/saveCustomer")
    public ResponseEntity saveCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            String response = customerService.saveCustomer(customerDTO);
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("06")) {
                responseDTO.setCode(VarList.RSP_Duplicate);
                responseDTO.setMessage("Already Registered.");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_Fail);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping (value = "/updateCustomer")
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            String response = customerService.updateCustomer(customerDTO);
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("01")) {
                responseDTO.setCode(VarList.RSP_No_Data_Found);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_Fail);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (value = "/viewCustomer")
    public ResponseEntity viewCustomer(){
        try {
            List<CustomerDTO> customerDTOList = customerService.viewCustomer();
            responseDTO.setCode(VarList.RSP_Success);
            responseDTO.setMessage("Saved Successfully.");
            responseDTO.setContent(customerDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping (value = "/searchCustomer/{CustomerId}")
    public ResponseEntity searchCustomer(@PathVariable int CustomerId){
        try {
            CustomerDTO customerDTO = customerService.searchCustomer(CustomerId);
            if (customerDTO != null){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Successful.");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_No_Data_Found);
                responseDTO.setMessage("No customer available for this customerId.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping (value = "/deleteCustomer/{CustomerId}")
    public ResponseEntity deleteCustomer(@PathVariable int CustomerId){
        try {
            String reponse = customerService.deleteCustomer(CustomerId);
            if (reponse.equals("00")){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Successful.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_No_Data_Found);
                responseDTO.setMessage("No customer available for this customerId.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
