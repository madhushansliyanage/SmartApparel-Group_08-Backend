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
    private int SmallSize;
    private int MediumSize;
    private int LargeSize;
    private String ClothMaterial;
    private String status;
}

//Status of an order throughout its lifecycle
//enum UpdateOrderStatus {
//    PENDING,    //Order pending.
//    STARTED,    //Order creation started.
//    PROCESSING, //Order is currently being processed.
//    QUALITY_CERTIFIED,  //Order quality certification completed.
//    SHIPPED,    //Order shipped to the customer.
//    DELIVERED   //Order  delivered to the customer.
//}
