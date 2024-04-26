package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee{
    // Employee ID
    @Id
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
    @Column(name = "phone_number")
    private String phoneNumber;

    // Employee date of birth
    @Column(name="dob")
    private Date dateOfBirth;

    // Bank account number
    @Column(name = "account_number")
    private String accountNumber;

    // Bank account holder name
    @Column(name = "holder_name")
    private String holderName;

    // Bank branch name
    @Column(name = "branch_name")
    private String branchName;

    // Bank name
    @Column(name = "bank_name")
    private String bankName;
}
