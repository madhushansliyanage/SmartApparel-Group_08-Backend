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
    private int OrderCoveredAmount;

    private int totalSize;

    public boolean get() {
        return true;
    }

    private boolean isInventorySufficient;

    public OrderDTO() {}

    public OrderDTO(boolean isInventorySufficient) {
        this.isInventorySufficient = isInventorySufficient;
    }

    public boolean isInventorySufficient() {
        return isInventorySufficient;
    }

    public void setInventorySufficient(boolean isInventorySufficient) {
        this.isInventorySufficient = isInventorySufficient;
    }

//    public int getOrderId() {
//        return OrderId;
//    }
//    public void setOrderId(int OrderId) {
//        this.OrderId = OrderId;
//    }
//
//    public String getOrderCustomerName() {
//        return OrderCustomerName;
//    }
//    public void setOrderCustomerName(String OrderCustomerName) {
//        this.OrderCustomerName = OrderCustomerName;
//    }
//
//    public double getOrderAgreedPrice() {
//        return OrderAgreedPrice;
//    }
//    public void setOrderAgreedPrice(double OrderAgreedPrice) {
//        this.OrderAgreedPrice = OrderAgreedPrice;
//    }
//
//    public String getModelName() {
//        return ModelName;
//    }
//    public void setModelName(String ModelName) {
//        this.ModelName = ModelName;
//    }
//
//    public int getSmallSize() {
//        return SmallSize;
//    }
//    public void setSmallSize(int SmallSize) {
//        this.SmallSize = SmallSize;
//    }
//
//    public int getMediumSize() {
//        return MediumSize;
//    }
//    public void setMediumSize(int MediumSize) {
//        this.MediumSize = MediumSize;
//    }
//
//    public int getLargeSize() {
//        return LargeSize;
//    }
//    public void setLargeSize(int LargeSize) {
//        this.LargeSize = LargeSize;
//    }
//
//    public String getClothMaterial() {
//        return ClothMaterial;
//    }
//    public void setClothMaterial(String ClothMaterial) {
//        this.ClothMaterial = ClothMaterial;
//    }
//
////    public int getQuantity() {
////        return Quantity;
////    }
////    public void setQuantity(int Quantity) {
////        this.Quantity = Quantity;
////    }
//
//    public String getOrderStatus() {
//        return OrderStatus;
//    }
//    public void setOrderStatus(String OrderStatus) {
//        this.OrderStatus = OrderStatus;
//    }
//
//    public int getOrderCoveredAmount() {
//        return OrderCoveredAmount;
//    }
//    public void setOrderCoveredAmount(int OrderCoveredAmount) {
//        this.OrderCoveredAmount = OrderCoveredAmount;
//    }
}
