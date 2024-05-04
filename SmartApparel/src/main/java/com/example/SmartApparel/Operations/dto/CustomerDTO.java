package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDTO {
    // Properties of the CustomerDTO class
    private int CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerEmail;
    private String CustomerCompanyName;
    private String CustomerReference;
    private int CustomerPhoneNum;
    private String CustomerPassword;
}
