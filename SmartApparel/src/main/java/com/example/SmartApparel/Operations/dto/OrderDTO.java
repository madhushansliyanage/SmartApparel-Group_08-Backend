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
    private String OrderAgreedPrice;
    private int OrderSize;
    private int OrderAmount;
}

//Status of an order throughout its lifecycle
enum UpdateOrderStatus {
    PENDING,    //Order pending.
    CREATED,    //Order created.
    PROCESSING, //Order is currently being processed.
    QUALITY_CERTIFIED,  //Order quality certification completed.
    SHIPPED,    //Order shipped to the customer.
    DELIVERED   //Order  delivered to the customer.
}
