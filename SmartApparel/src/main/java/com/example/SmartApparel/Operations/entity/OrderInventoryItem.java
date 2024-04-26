package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

// Annotation to specify the table name in the database
@Table(name = "Order")
@Table(name = "Inventory") //Where the sufficient materials want to check
public class OrderInventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderId;
    private int AvailableQuantity;

    public int getOrderId(){
        return OrderId;
    }
    public void setOrderId(int OrderId){
        this.OrderId = OrderId;
    }

    public int getAvailableQuantity(){

        return AvailableQuantity;
    }
    public void setAvailableQuantity(int AvailableQuantity){
        this.AvailableQuantity = AvailableQuantity;
    }
}
