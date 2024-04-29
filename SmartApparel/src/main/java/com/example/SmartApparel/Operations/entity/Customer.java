package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

// Annotation to specify the table name in the database
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerEmail;
    private String CustomerCompanyName;
    private String CustomerReference;
    private String CustomerPhoneNum;
    private String CustomerPassword;
}
