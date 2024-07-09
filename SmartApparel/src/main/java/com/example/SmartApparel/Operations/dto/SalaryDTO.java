package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) class for Salary.
 * This DTO class is used for transferring Salary data between different layers of the application.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryDTO {

    // Unique identifier for the salary record
    private int salaryId;

    // Employee ID associated with the salary record
    private String empId;

    // Status of the salary (e.g., paid, pending)
    private String status;

    // Year and month for which the salary is applicable, in the format YYYY-MM
    private String yearNMonth;

    // Basic salary amount for the month
    private BigDecimal basic;

    // OT for the month
    private BigDecimal overTime;

    // Employee's contribution to the Employees' Provident Fund (EPF)
    private BigDecimal epfByEmployee;

    // Company's contribution to the Employees' Provident Fund (EPF)
    private BigDecimal epfByCompany;

    // Employees' Trust Fund (ETF) payment
    private BigDecimal etfPayment;

    // Allowance1 for the month
    private BigDecimal allowance1;

    // Allowance2 for the month
    private BigDecimal allowance2;

    // Net salary after all deductions
    private BigDecimal netSalary;
}
