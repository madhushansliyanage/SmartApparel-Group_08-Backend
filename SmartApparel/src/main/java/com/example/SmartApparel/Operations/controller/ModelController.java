package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.ModelDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.service.ModelService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/model")
@CrossOrigin
public class ModelController {

    // Autowired ResponseDTO and CustomerService
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private ModelService modelService;

    // Save model details
    @PostMapping(value = "/saveModel")
    public ResponseEntity saveModel(@RequestBody ModelDTO modelDTO){
        try {
            String response = modelService.saveModel(modelDTO);
            // Handle different responses from the service
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(modelDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Already Registered.");
                responseDTO.setContent(modelDTO);
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
}
