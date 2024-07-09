package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) Class for Salary Parameters.
 * This DTO class is used for transferring SalaryParameter data between different layers of the application.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryParameterDTO {

    // Unique identifier for the salary parameter
    private int salaryParameterId;

    // Job position associated with the salary parameter
    private String position;

    // Basic salary amount
    private BigDecimal basicSalary;

    // Employee Provident Fund (EPF) contribution by the employee
    private BigDecimal epfByEmployee;

    // Employee Provident Fund (EPF) contribution by the company
    private BigDecimal epfByCompany;

    // Employee Trust Fund (ETF) contribution
    private BigDecimal etf;

    //Allowance amount pay to the employee's position
    private BigDecimal allowance1;

    //Allowance amount pay to the employee's position
    private BigDecimal allowance2;
}
