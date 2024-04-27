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
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderId;
    private String OrderCustomerName;
    private String OrderAddress;
    private String OrderEmail;
    private String OrderPhoneNum;
    private int Quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus Status;


    public int getOrderId(){
        return OrderId;
    }
    public void setOrderId(int OrderId){
        this.OrderId = OrderId;
    }

    public int getOrderCustomerName(){
        return OrderCustomerName;
    }
    public void setOrderCustomerName(int OrderCustomerName){
        this.OrderCustomerName = OrderCustomerName;
    }

    public int getOrderAddress(){
        return OrderAddress;
    }
    public void setOrderAddress(int OrderAddress){
        this.OrderAddress = OrderAddress;
    }

    public int getOrderEmail(){
        return OrderEmail;
    }
    public void setOrderEmail(int OrderEmail){
        this.OrderEmail = OrderEmail;
    }

    public int getOrderPhoneNum(){
        return OrderPhoneNum;
    }
    public void setOrderPhoneNum(int OrderPhoneNum){
        this.OrderPhoneNum = OrderPhoneNum;
    }

    public int getQuantity(){
        return Quantity;
    }
    public void setQuantity(int Quantity){
        this.Quantity = Quantity;
    }

    public OrderStatus getStatus(){
        return Status;
    }
    public void setStatus(int Status){
        this.Status = Status;
    }
}
