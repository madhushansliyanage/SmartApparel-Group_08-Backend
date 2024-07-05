package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for transferring revenue information.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RevenueDTO {

    private int Revenue_ID;
    private String Date;
    private String Description;
    private String Cheque_Id;
    private String Order_Id;
    private String Status;
    private Double Amount;
}
