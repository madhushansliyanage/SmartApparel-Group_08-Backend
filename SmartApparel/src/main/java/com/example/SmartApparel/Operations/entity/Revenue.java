package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing the Revenue table.
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Revenue")
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Revenue_ID;
    private String Date;
    private String Description;
    private String Cheque_Id;
    private String Order_Id;
    private String Status;
    private Double Amount;
}
