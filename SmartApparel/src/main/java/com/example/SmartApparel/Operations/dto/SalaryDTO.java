package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryDTO {
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
