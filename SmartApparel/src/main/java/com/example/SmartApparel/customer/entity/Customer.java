package com.example.customerAMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Customer")

public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerEmail;
    private String CustomerPhoneNum;
}