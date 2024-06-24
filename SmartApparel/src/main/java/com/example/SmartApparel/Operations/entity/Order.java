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
    private double OrderAgreedPrice;
    private String ModelName;
    private int SmallSize;
    private int MediumSize;
    private int LargeSize;
    private String ClothMaterial;
    private String OrderStatus;

    @Lob
    private byte[] billPdf;

//    private int Quantity;

    @Enumerated(EnumType.STRING)

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

    public String getModelName() {
        return ModelName;
    }
    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    public int getSmallSize() {
        return SmallSize;
    }
    public void setSmallSize(int SmallSize) {
        this.SmallSize = SmallSize;
    }

    public int getMediumSize() {
        return MediumSize;
    }
    public void setMediumSize(int MediumSize) {
        this.MediumSize = MediumSize;
    }

    public int getLargeSize() {
        return LargeSize;
    }
    public void setLargeSize(int LargeSize) {
        this.LargeSize = LargeSize;
    }

    public String getClothMaterial() {
        return ClothMaterial;
    }
    public void setClothMaterial(String ClothMaterial) {
        this.ClothMaterial = ClothMaterial;
    }

//    public int getQuantity() {
//        return Quantity;
//    }
//    public void setQuantity(int Quantity) {
//        this.Quantity = Quantity;
//    }

    public String getOrderStatus() {
        return OrderStatus;
    }
    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public byte[] getBillPdf() {
        return billPdf;
    }
    public void setBillPdf(byte[] billPdf) {
        this.billPdf = billPdf;
    }
}


