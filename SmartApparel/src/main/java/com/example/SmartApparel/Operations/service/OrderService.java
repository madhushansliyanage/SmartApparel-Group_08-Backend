package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.OrderDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
//import com.example.SmartApparel.Operations.dto.UpdateOrderStatus;
import com.example.SmartApparel.Operations.entity.Order;
import com.example.SmartApparel.Operations.repo.OrderRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    // Autowired OrderRepo and ModelMapper
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;

    private ResponseDTO responseDTO;

    // Method to save a new order
    public String saveOrder(OrderDTO orderDTO){
        // Check if a order with the given ID already exists
        if (orderRepo.existsById(orderDTO.getOrderId())){
            return VarList.RSP_DUPLICATED;
        }else {
            // Save the order to the database
            orderRepo.save(modelMapper.map(orderDTO, Order.class));
            return VarList.RSP_SUCCESS;
        }
    }

    // Method to update an existing order
    public String updateOrder(OrderDTO orderDTO){
        // Check if a order with the given ID exists
        if (orderRepo.existsById(orderDTO.getOrderId())){
            // Update the order in the database
            orderRepo.save(modelMapper.map(orderDTO,Order.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Method to retrieve all orders
    public List<OrderDTO> viewOrder(){
        // Retrieve all orders from the database
        List<Order> orderList = orderRepo.findAll();
        // Map the list of entities to a list of DTOs
        return modelMapper.map(orderList, new TypeToken<ArrayList<OrderDTO>>(){}.getType());
    }

    // Method to delete a order
    public String deleteOrder(int OrderId){
        // Check if a order with the given ID exists
        if (orderRepo.existsById(OrderId)){
            // Delete the customer from the database
            orderRepo.deleteById(OrderId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

//    public boolean checkInventoryAndAllocateMaterials(Order order, Object UpdateOrderStatus) {
//        order = orderRepo.findById(order.getOrderId()).orElse(null);
//        if (order != null) {
//            // Perform inventory check logic
//            if (order.getOrderAmount() > order.getQuantity()//get current order quantity ){
//                boolean inventoryCheckPassed = performInventoryCheck(order);
//                if (inventoryCheckPassed) {
//                    // Allocate materials and update order status
//                    order.getClass(UpdateOrderStatus.PENDING);
//                    orderRepo.save(order);
//                    return modelMapper.map(order, OrderDTO.class);
//                }
//            }
//            else {
//                // Handle insufficient inventory scenario
//                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
//                responseDTO.setMessage("Insufficient amount of materials.");
//                //responseDTO.setContent(null);
//                return Boolean.parseBoolean(null);
//            }
//        }
//        return Boolean.parseBoolean(null); // Return null if the order is not found
//    }
//
//    private boolean performInventoryCheck(Order orderId) {
//        boolean sufficientMaterials = checkInventoryAndAllocateMaterials(orderId, UpdateOrderStatus);
//
//        if (sufficientMaterials) {
//            // Allocate materials and update order status
//            UpdateOrderStatus(orderId);
//        } else {
//            // Handle case when there are insufficient materials
//            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
//            responseDTO.setMessage("Insufficient amount of materials.");
//            //responseDTO.setContent(null);
//            //return null;
//        }
//        return true;
//    }

//    //Method for Order Status
//    public Order UpdateOrderStatus(int orderId) {
//        // Retrieve the order from the repository by its ID
//        Order order = orderRepo.findById(orderId).orElse(null);
//
//        // If the order is found, update its status
//        if (order != null) {
//            switch (order.getStatus()) {
//                case "CREATED":
//                    order.setStatus(UpdateOrderStatus.CREATED);   // Update order status to CREATED
//                    //performQualityCertification(order);    // Perform quality certification tasks
//                    return orderRepo.save(order);
//
//                case "PROCESSING":
//                    order.setStatus(UpdateOrderStatus.PROCESSING);    // Update order status to PROCESSING
//                    //performQualityCertification(order); // Perform quality certification tasks
//                    return orderRepo.save(order);
//
//                case "QUALITY_CERTIFIED":
//                    order.setStatus(UpdateOrderStatus.QUALITY_CERTIFIED); // Update order status to QUALITY_CERTIFIED
//                    //performQualityCertification(order); // Perform quality certification tasks
//                    return orderRepo.save(order);
//                case "SHIPPED":
//                    order.setStatus(UpdateOrderStatus.SHIPPED); // Update order status to SHIPPED
//                    //performQualityCertification(order); // Perform quality certification tasks
//                    return orderRepo.save(order);
//                case "DELIVERED":
//                    order.setStatus(UpdateOrderStatus.DELIVERED); // Update order status to DELIVERED
//                    //performQualityCertification(order); // Perform quality certification tasks
//                    return orderRepo.save(order);
//            }
//            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
//            responseDTO.setMessage("Data not found");
//            responseDTO.setContent(null);
//
//        }
//        return null;
//    }

//    public Order OrderStatus(int orderId) {
//        return null;
//    }

    // Method to perform quality certification tasks
//    private void performQualityCertification(Order order) {
//        // Implement quality certification tasks
//    }

}
