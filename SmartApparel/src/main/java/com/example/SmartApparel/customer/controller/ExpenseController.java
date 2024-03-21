package com.example.SmartApparel.customer.controller;

import com.example.SmartApparel.customer.dto.CustomerDTO;
import com.example.SmartApparel.customer.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/expense")
@CrossOrigin
public class ExpenseController {

    @GetMapping("getExpense")
    public String getExpense(){
        return "User Details";
    }

    @PutMapping("putExpense")
    public String putExpense(){
        return "Put User Details";
    }

    @PostMapping("postExpense")
    public String postExpense(){
        return "Post User Details";
    }

}
