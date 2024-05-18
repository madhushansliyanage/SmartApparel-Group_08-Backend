package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.CustomerDTO;
import com.example.SmartApparel.Operations.dto.OrderDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.entity.Order;
import com.example.SmartApparel.Operations.service.OrderService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    // Autowired ResponseDTO and OrderService
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private OrderService orderService;

    // Save order details
    @PostMapping(value = "/saveOrder")
    public ResponseEntity saveOrder(@RequestBody OrderDTO orderDTO){
        try {
            String response = orderService.saveOrder(orderDTO);
            // Handle different responses from the service
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Already Registered.");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping(value = "/saveOrder")
//    public ResponseEntity<ResponseDTO> saveOrder(@RequestBody OrderDTO orderDTO){
//        try {
//            String response = orderService.saveOrder(orderDTO);
//            if (response.equals(VarList.RSP_SUCCESS)){
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Saved Successfully.");
//                responseDTO.setContent(orderDTO);
//                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
//            } else if (response.equals(VarList.RSP_DUPLICATED)) {
//                responseDTO.setCode(VarList.RSP_DUPLICATED);
//                responseDTO.setMessage("Already Registered.");
//                responseDTO.setContent(orderDTO);
//                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//            } else {
//                responseDTO.setCode(VarList.RSP_FAIL);
//                responseDTO.setMessage("Error");
//                responseDTO.setContent(null);
//                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            responseDTO.setCode(VarList.RSP_ERROR);
//            responseDTO.setMessage(e.getMessage());
//            responseDTO.setContent(null);
//            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    // Update order details
    @PutMapping (value = "/updateOrder")
    public ResponseEntity updateOrder(@RequestBody OrderDTO orderDTO){
        try {
            String response = orderService.updateOrder(orderDTO);
            // Handle different responses from the service
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @PutMapping(value = "/updateOrder")
//    public ResponseEntity<ResponseDTO> updateOrder(@RequestBody OrderDTO orderDTO){
//        try {
//            String response = orderService.updateOrder(orderDTO);
//            if (response.equals(VarList.RSP_SUCCESS)){
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Updated Successfully.");
//                responseDTO.setContent(orderDTO);
//                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
//            } else if (response.equals(VarList.RSP_NO_DATA_FOUND)) {
//                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
//                responseDTO.setMessage("Not a Registered Order");
//                responseDTO.setContent(orderDTO);
//                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//            } else {
//                responseDTO.setCode(VarList.RSP_FAIL);
//                responseDTO.setMessage("Error");
//                responseDTO.setContent(null);
//                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            responseDTO.setCode(VarList.RSP_ERROR);
//            responseDTO.setMessage(e.getMessage());
//            responseDTO.setContent(null);
//            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // View all orders
    @GetMapping (value = "/viewOrder")
    public ResponseEntity viewOrder(){
        try {
            // Retrieve list of orders
            List<OrderDTO> orderDTOList = orderService.viewOrder();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Saved Successfully.");
            responseDTO.setContent(orderDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping(value = "/viewOrder")
//    public ResponseEntity<ResponseDTO> viewOrder(){
//        try {
//            List<OrderDTO> orderDTOList = orderService.viewOrder();
//            responseDTO.setCode(VarList.RSP_SUCCESS);
//            responseDTO.setMessage("Retrieved Successfully.");
//            responseDTO.setContent(orderDTOList);
//            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            responseDTO.setCode(VarList.RSP_ERROR);
//            responseDTO.setMessage(e.getMessage());
//            responseDTO.setContent(null);
//            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @GetMapping(value = "/viewOrder/{orderId}")
    public ResponseEntity<ResponseDTO> viewCustomerById(@PathVariable("orderId") Integer OrderId) {
        try {
            OrderDTO orderDTO = orderService.viewOrderById(OrderId);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Customer retrieved successfully.");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Delete a order
    @DeleteMapping (value = "/deleteOrder/{OrderId}")
    public ResponseEntity deleteOrder(@PathVariable int OrderId){
        try {
            String response = orderService.deleteOrder(OrderId);
            // Handle different responses from the service
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successful.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No order available.");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping(value = "/deleteOrder/{OrderId}")
//    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable int OrderId){
//        try {
//            String response = orderService.deleteOrder(OrderId);
//            if (response.equals(VarList.RSP_SUCCESS)){
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Deleted Successfully.");
//                responseDTO.setContent(null);
//                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
//            } else {
//                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
//                responseDTO.setMessage("No Order Found.");
//                responseDTO.setContent(null);
//                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            responseDTO.setCode(VarList.RSP_ERROR);
//            responseDTO.setMessage(e.getMessage());
//            responseDTO.setContent(null);
//            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    // To get Total sum of Expenses
    @GetMapping("/completedOrderId")
    public ResponseEntity<ResponseDTO> getCompletedOrderIds() {

        try {
            List<Integer> orderCompleted = orderService.getCompletedOrderIds();

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Total completed order list successfully.");
            responseDTO.setContent(orderCompleted);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Failed to retrieve completed order list : " + e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
