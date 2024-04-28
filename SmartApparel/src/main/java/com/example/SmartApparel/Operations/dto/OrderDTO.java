package com.example.SmartApparel.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDTO {

    // Properties of the OrderDTO class
    private int OrderId;
    private String OrderCustomerName;
    private String OrderAddress;
    private String OrderEmail;
    private String OrderStatus;
    private String OrderPhoneNum;
}
