package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data Transfer Object (DTO) for transferring expense information.
 */

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
