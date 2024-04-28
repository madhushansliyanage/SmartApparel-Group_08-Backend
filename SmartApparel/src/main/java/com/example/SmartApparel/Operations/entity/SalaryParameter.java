package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Salary Parameter entity.
 * This entity stores information about various parameters related to salary calculation.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryParameter {

    /**
     * Unique identifier for the salary parameter.
     * This ID is automatically generated when a new salary parameter is created.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Employee Provident Fund (EPF) contribution.
     * This field stores the amount contributed by the employee towards EPF.
     */
    private float epfByEmployee;

    /**
     * Employee Provident Fund (EPF) contribution.
     * This field stores the amount contributed by the company towards EPF.
     */
    private float epfByCompany;

    /**
     * Employee Trust Fund (ETF) contribution.
     * This field stores the amount contributed towards ETF.
     */
    private float etf;
}

