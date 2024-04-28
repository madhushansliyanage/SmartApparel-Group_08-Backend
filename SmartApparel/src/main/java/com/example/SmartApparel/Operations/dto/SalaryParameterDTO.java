package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) Class for Salary Parameters.
 * This DTO class is used for transferring SalaryParameter data between different layers of the application.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryParameterDTO {

    /**
     * Unique identifier for the salary parameter.
     * This ID uniquely identifies each salary parameter.
     */
    private int salaryParameterId;

    /**
     * Job position associated with the salary parameter.
     * This field specifies the job position to which this salary parameter applies.
     */
    private String position;

    /**
     * Basic salary amount.
     * This field represents the base salary amount for the specified job position.
     */
    private float basicSalary;

    /**
     * Employee Provident Fund (EPF) contribution by the employee.
     * This field stores the percentage contributed by the employee towards EPF.
     */
    private float epfByEmployee;

    /**
     * Employee Provident Fund (EPF) contribution by the company.
     * This field stores the percentage contributed by the company towards EPF.
     */
    private float epfByCompany;

    /**
     * Employee Trust Fund (ETF) contribution.
     * This field stores the percentage contributed towards ETF.
     */
    private float etf;
}
