package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.OrderDTO;
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

    // Method to save a new order
    public String saveOrder(OrderDTO orderDTO){
        // Check if a order with the given ID already exists
        if (orderRepo.existsById(orderDTO.getOrderId())){
            return VarList.RSP_Duplicate;
        }else {
            // Save the order to the database
            orderRepo.save(modelMapper.map(orderDTO, Order.class));
            return VarList.RSP_Success;
        }
    }

    // Method to update an existing order
    public String updateOrder(OrderDTO orderDTO){
        // Check if a order with the given ID exists
        if (orderRepo.existsById(orderDTO.getOrderId())){
            // Update the order in the database
            orderRepo.save(modelMapper.map(orderDTO,Order.class));
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }

    // Method to retrieve all orders
    public List<OrderDTO> viewOrder(){
        // Retrieve all orders from the database
        List<Order> orderList = orderRepo.findAll();
        // Map the list of entities to a list of DTOs
        return modelMapper.map(customerList, new TypeToken<ArrayList<OrderDTO>>(){}.getType());
    }

    // Method to delete a order
    public String deleteOrder(){
        // Check if a order with the given ID exists
        if (customerRepo.existsById(CustomerId)){
            // Delete the customer from the database
            customerRepo.deleteById(CustomerId);
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }
}
