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
import java.util.stream.Collectors;

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
        // Check if an order with the given ID already exists
        if (orderRepo.existsById(orderDTO.getOrderId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            // Save the order to the database
            orderRepo.save(modelMapper.map(orderDTO, Order.class));
            return VarList.RSP_SUCCESS;
        }
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

    public List<OrderDTO> ShippedOrders() {
        List<Order> shippedOrders = orderRepo.findByOrderStatus("Shipped");
        return shippedOrders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderCustomerName(order.getOrderCustomerName());
        orderDTO.setOrderAgreedPrice(order.getOrderAgreedPrice());
        orderDTO.setModelName(order.getModelName());
        orderDTO.setSmallSize(order.getSmallSize());
        orderDTO.setMediumSize(order.getMediumSize());
        orderDTO.setLargeSize(order.getLargeSize());
        orderDTO.setClothMaterial(order.getClothMaterial());
        orderDTO.setOrderStatus(order.getOrderStatus());
        return orderDTO;
    }

//    public List<Order> getShippedOrders() {
//        return orderRepo.findByOrderStatus("Shipped");
//    }


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

    public List<Integer> getCompletedOrderIds() {

        // Retrieve all orders from the database
        List<Integer> orderIdList = orderRepo.getCompletedOrders();
        // Map the list of entities to a list of DTOs
        return modelMapper.map(orderIdList, new TypeToken<ArrayList<Integer>>(){}.getType());
    }


}
