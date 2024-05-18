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
    private double OrderAgreedPrice;
    private String ModelName;
    private int SmallSize;
    private int MediumSize;
    private int LargeSize;
    private String ClothMaterial;
    private String OrderStatus;
}
