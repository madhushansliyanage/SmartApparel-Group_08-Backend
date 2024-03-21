package com.example.SmartApparel.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseDTO {

    private int Expense_ID;
    private String Date;
    private String Description;
    private String Category;
    private Double Amount;
}
