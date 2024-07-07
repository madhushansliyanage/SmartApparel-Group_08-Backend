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
import java.util.Optional;

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
    public String saveOrder(OrderDTO orderDTO) {
        // Map OrderDTO to Order entity
        Order order = modelMapper.map(orderDTO, Order.class);
        // Save the order to the database
        orderRepo.save(order);
        return VarList.RSP_SUCCESS;
    }


    // Method to update an existing order
    public String updateOrder(OrderDTO orderDTO){
        // Check if an order with the given ID exists
        if (orderRepo.existsById(orderDTO.getOrderId())){
            // Update the order in the database
            orderRepo.save(modelMapper.map(orderDTO,Order.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Method to update an existing order by orderId
    public String updateOrderById(OrderDTO orderDTO) {
        try {
            // Check if an order with the given ID exists
            if (orderRepo.existsById(orderDTO.getOrderId())) {
                // Update the order in the database
                orderRepo.save(modelMapper.map(orderDTO, Order.class));
                return VarList.RSP_SUCCESS;
            } else {
                return VarList.RSP_NO_DATA_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }

    // Method to retrieve all orders
    public List<OrderDTO> viewOrder(){
        // Retrieve all orders from the database
        List<Order> orderList = orderRepo.findAll();
        // Map the list of entities to a list of DTOs
        return modelMapper.map(orderList, new TypeToken<ArrayList<OrderDTO>>(){}.getType());
    }

    // Method to view an order by ID
    public OrderDTO viewOrderById(Integer OrderId) throws Exception {
        // Retrieve order by ID from the repository
        Optional<Order> optionalOrder = orderRepo.findById(OrderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return modelMapper.map(order, OrderDTO.class); // Assuming you use ModelMapper to convert entity to DTO
        } else {
            throw new Exception("Order not found with ID: " + OrderId);
        }
    }

    // Method to delete an order by ID
    public String deleteOrder(int OrderId) {
        // Check if an order with the given ID exists
        if (orderRepo.existsById(OrderId)) {
            // Delete the order from the database
            orderRepo.deleteById(OrderId);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public Order getOrderById(Integer OrderId) {
        return orderRepo.findById(OrderId).orElse(null);
    }


//    public boolean checkInventory(String modelName, int totalSize, String clothMaterial) {
//        Model model = modelRepository.findByName(modelName);
//        int requiredCloth = totalSize * model.getClothAmount();
//        int requiredButton = totalSize * model.getButtonAmount();
//        int requiredZipper = totalSize * model.getZipperAmount();
//        int requiredElastic = totalSize * model.getElasticAmount();
//
//        Inventory inventory = OrderRepo.findByClothMaterial(clothMaterial);
//
//        return inventory.getClothMaterial() >= requiredCloth &&
//                inventory.getButton() >= requiredButton &&
//                inventory.getZipper() >= requiredZipper &&
//                inventory.getElastic() >= requiredElastic;
//    }



    public List<Integer> getCompletedOrderIds() {

        // Retrieve all orders from the database
        List<Integer> orderIdList = orderRepo.getCompletedOrders();
        // Map the list of entities to a list of DTOs
        return modelMapper.map(orderIdList, new TypeToken<ArrayList<Integer>>(){}.getType());
    }
}
