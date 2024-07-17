package com.example.SmartApparel.Operations.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Embeddable
@Table(name = "userInfo")
public class UserInfo {
    private String username;
    private String email;
    private String contactNo;
}

