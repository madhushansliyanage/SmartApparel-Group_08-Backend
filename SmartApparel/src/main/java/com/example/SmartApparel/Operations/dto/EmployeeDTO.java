package com.example.SmartApparel.Operations.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

/**
 * Data Transfer Object (DTO) class for Employee.
 * This DTO class is used for transferring Employee data between different layers of the application.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    // Employee ID
    private String empId;

    // Employee name
    private String name;

    // Employee address
    private String address;

    // National Identification Card number
    private String nic;

    // Employee position
    private String position;

    // Employee email
    private String email;

    // Employee password
    private String password;

    // Employee phone number
    private String phoneNumber;

    // Employee date of birth
    private Date dateOfBirth;

    // Bank account number
    private String accountNumber;

    // Bank account holder name
    private String holderName;

    // Bank branch name
    private String branchName;

    // Bank name
    private String bankName;
}
