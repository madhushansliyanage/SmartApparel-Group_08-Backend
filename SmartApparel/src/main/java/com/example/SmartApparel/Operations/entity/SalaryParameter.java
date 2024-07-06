package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents a Salary Parameter entity.
 * This entity stores information about various parameters related to salary calculation.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryParameter {

    // Unique identifier for the salary parameter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryParameterId;

    // Job position associated with the salary parameter
    @Column(nullable = false)
    private String position;

    // Basic salary amount
    @Column(precision = 8,scale = 2,nullable = false)
    private BigDecimal basicSalary;

    // Employee Provident Fund (EPF) contribution percentage by employee
    @Column(precision = 5,scale = 2,nullable = false)
    private BigDecimal epfByEmployee;

    // Employee Provident Fund (EPF) contribution percentage by company
    @Column(precision = 5,scale = 2,nullable = false)
    private BigDecimal epfByCompany;

    // Employee Trust Fund (ETF) contribution percentage(by company)
    @Column(precision = 5,scale = 2,nullable = false)
    private BigDecimal etf;

    //Allowance amount pay to the employee's position
    @Column(precision = 8,scale = 2,nullable = false)
    private BigDecimal allowance;
}
