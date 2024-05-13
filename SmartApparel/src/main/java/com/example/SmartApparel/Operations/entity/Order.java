package com.example.SmartApparel.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

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
    private int OrderAgreedPrice;
    private int SmallSize;
    private int MediumSize;
    private int LargeSize;
    private String ClothMaterial;

    private int Quantity;

    private int status;

    @Enumerated(EnumType.STRING)
    //private UpdateOrderStatus Status;

    public Order save(Order order) {
        return null;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public String getOrderCustomerName() {
        return OrderCustomerName;
    }

    public void setOrderCustomerName(String OrderCustomerName) {
        this.OrderCustomerName = OrderCustomerName;
    }

    public double getOrderAgreedPrice() {
        return OrderAgreedPrice;
    }

    public void setOrderAgreedPrice(double OrderAgreedPrice) {
        this.OrderAgreedPrice = OrderAgreedPrice;
    }

    public String getOrderSize() {
        return OrderSize;
    }

    public void setOrderSize(String OrderSize) {
        this.OrderSize = OrderSize;
    }

    public int getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(int OrderAmount) {
        this.OrderAmount = OrderAmount;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}