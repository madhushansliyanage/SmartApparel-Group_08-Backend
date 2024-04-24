package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RevenueDTO {

    private int Revenue_ID;
    private String Date;
    private String Description;
    private String Category;
    private Double Amount;
}
