package com.example.customerAMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDTO {
    private int CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerEmail;
    private String CustomerPhoneNum;
}
