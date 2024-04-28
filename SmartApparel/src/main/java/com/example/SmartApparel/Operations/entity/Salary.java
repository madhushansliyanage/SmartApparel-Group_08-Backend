package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryId;
    private String empId;
    private String status;
    private String yearNMonth;
    private float basic;
    private float epfByEmployee;
    private float epfByCompany;
    private float etfPayment;
    private float netSalary;
}