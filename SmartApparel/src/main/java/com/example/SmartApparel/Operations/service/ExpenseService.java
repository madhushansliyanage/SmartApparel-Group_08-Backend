package com.example.SmartApparel.Operations.service;


import com.example.SmartApparel.Operations.dto.ExpenseDTO;
import com.example.SmartApparel.Operations.entity.Expense;
import com.example.SmartApparel.Operations.repo.ExpenseRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveExpense(ExpenseDTO expenseDTO){
        if (expenseRepo.existsById(expenseDTO.getExpense_ID())){
            return VarList.RSP_Duplicate;
        }else {
            expenseRepo.save(modelMapper.map(expenseDTO, Expense.class));
            return VarList.RSP_Success;
        }
    }

    public String updateExpense(ExpenseDTO expenseDTO){
        if (expenseRepo.existsById(expenseDTO.getExpense_ID())){
            expenseRepo.save(modelMapper.map(expenseDTO,Expense.class));
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }

    public List<ExpenseDTO> viewExpense(){
        List<Expense> expenseList = expenseRepo.findAll();
        return modelMapper.map(expenseList, new TypeToken<ArrayList<ExpenseDTO>>(){}.getType());
    }

    public ExpenseDTO searchExpense(int ExpenseId){ //need to search using customer name
        if (expenseRepo.existsById(ExpenseId)){
            Expense expense = expenseRepo.findById(ExpenseId).orElse(null);
            return modelMapper.map(expense, ExpenseDTO.class);
        }else {
            return null;
        }
    }

    public String deleteExpense(int ExpenseId){
        if (expenseRepo.existsById(ExpenseId)){
            expenseRepo.deleteById(ExpenseId);
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }

}
