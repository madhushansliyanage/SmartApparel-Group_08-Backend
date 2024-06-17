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

//    public boolean isOrderShipped(Integer OrderId) {
//        Optional<Order> order = viewOrderById(OrderId);
//        return order.isPresent() && "Shipped".equalsIgnoreCase(order.get().getOrderStatus());
//    }

//    public boolean isOrderShipped(Integer OrderId) {
//        try {
//            OrderDTO order = viewOrderById(OrderId);
//            return order.get() && "Shipped".equalsIgnoreCase(String.valueOf(order.get().getClass()));
//        } catch (Exception e) {
//            // Handle exception (e.g., log it)
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean isOrderShipped(Integer orderId) {
        try {
            OrderDTO order = viewOrderById(orderId);
            return order != null && "Shipped".equalsIgnoreCase(order.getOrderStatus());
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return false;
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


    // Method to search for an order by ID
//    public OrderDTO searchOrder(int OrderId){
//        // Check if an order with the given ID exists
//        if (orderRepo.existsById(OrderId)){
//            // Retrieve the order from the database
////            Order order = orderRepo.findById(OrderId).orElse(null);
//            Order order = orderRepo.searchOrder(OrderId);
//            // Map the entity to a DTO and return
//            return modelMapper.map(order,OrderDTO.class);
//        }else {
//            return null;
//        }
//    }

    public List<Integer> getCompletedOrderIds() {

        // Retrieve all orders from the database
        List<Integer> orderIdList = orderRepo.getCompletedOrders();
        // Map the list of entities to a list of DTOs
        return modelMapper.map(orderIdList, new TypeToken<ArrayList<Integer>>(){}.getType());
    }


}
