package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Expense")

public class Expense {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int Expense_ID;
    private String Date;
    private String Description;
    private String Category;
    private Double Amount;

}