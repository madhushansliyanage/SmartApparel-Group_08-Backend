package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.OrderDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.entity.Order;
import com.example.SmartApparel.Operations.service.OrderService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping(value = "/saveOrder")
    public ResponseEntity<ResponseDTO> saveOrder(@RequestBody OrderDTO orderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            String response = orderService.saveOrder(orderDTO);
            if (response.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Saved Successfully.");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (response.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Already Registered.");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error occurred while saving order: {}", e.getMessage());
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update order details
    @PutMapping (value = "/updateOrder")
    public ResponseEntity updateOrder(@RequestBody OrderDTO orderDTO){
        try {
            String response = orderService.updateOrder(orderDTO);
            // Handle different responses from the service
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Updated Successfully.");
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

    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<?> updateOrderById(@PathVariable Integer OrderId, @RequestBody OrderDTO orderDTO) {
        orderDTO.setOrderId(OrderId); // Set the orderId in the DTO
        String response = orderService.updateOrderById(orderDTO);
        if (VarList.RSP_SUCCESS.equals(response)) {
            return ResponseEntity.ok("Order updated successfully");
        } else if (VarList.RSP_NO_DATA_FOUND.equals(response)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order");
        }
    }

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

    //View order details related to Id
    @GetMapping(value = "/viewOrder/{OrderId}")
    public ResponseEntity<ResponseDTO> viewOrderById(@PathVariable("OrderId") Integer OrderId) {
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

    @GetMapping("/checkShipped/{OrderId}")
    public ResponseEntity<ResponseDTO> checkShipped(@PathVariable Integer OrderId) {
        Order order = orderService.getOrderById(OrderId);
        ResponseDTO responseDTO = new ResponseDTO(); // Ensure this is properly instantiated

        if (order != null && "Shipped".equalsIgnoreCase(order.getOrderStatus())) {
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Successful.");
            responseDTO.setContent(true);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } else {
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("Order is not yet shipped or Invalid OrderId");
            responseDTO.setContent(false);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/generateBill/{OrderId}")
    public ResponseEntity<ResponseDTO> generateBill(@PathVariable Integer OrderId) {
        Order order = orderService.getOrderById(OrderId);
        ResponseDTO responseDTO = new ResponseDTO();

        if (order != null) {
            double totalAmount = order.getOrderAgreedPrice() *
                    (order.getSmallSize() + order.getMediumSize() + order.getLargeSize());

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
            orderDetailsDTO.setOrder(order);
            orderDetailsDTO.setTotalAmount(totalAmount);

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Successful.");
            responseDTO.setContent(orderDetailsDTO);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("Invalid OrderId");
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    

    @GetMapping("/downloadReport/{OrderId}")
    public ResponseEntity<InputStreamResource> downloadReport(@PathVariable Integer OrderId) throws IOException {
        Order order = orderService.getOrderById(OrderId);

        if (order != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document() {
                @Override
                public int getLength() {
                    return 0;
                }

                @Override
                public void addDocumentListener(DocumentListener listener) {

                }

                @Override
                public void removeDocumentListener(DocumentListener listener) {

                }

                @Override
                public void addUndoableEditListener(UndoableEditListener listener) {

                }

                @Override
                public void removeUndoableEditListener(UndoableEditListener listener) {

                }

                @Override
                public Object getProperty(Object key) {
                    return null;
                }

                @Override
                public void putProperty(Object key, Object value) {

                }

                @Override
                public void remove(int offs, int len) throws BadLocationException {

                }

                @Override
                public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {

                }

                @Override
                public String getText(int offset, int length) throws BadLocationException {
                    return "";
                }

                @Override
                public void getText(int offset, int length, Segment txt) throws BadLocationException {

                }

                @Override
                public Position getStartPosition() {
                    return null;
                }

                @Override
                public Position getEndPosition() {
                    return null;
                }

                @Override
                public Position createPosition(int offs) throws BadLocationException {
                    return null;
                }

                @Override
                public Element[] getRootElements() {
                    return new Element[0];
                }

                @Override
                public Element getDefaultRootElement() {
                    return null;
                }

                @Override
                public void render(Runnable r) {

                }
            };

            document.addDocumentListener(new Paragraph("Order ID: " + order.getOrderId()));
            document.addDocumentListener(new Paragraph("Order Customer Name: " + order.getOrderCustomerName()));
            document.addDocumentListener(new Paragraph("Model Name: " + order.getModelName()));
            document.addDocumentListener(new Paragraph("Small Size: " + order.getSmallSize()));
            document.addDocumentListener(new Paragraph("Medium Size: " + order.getMediumSize()));
            document.addDocumentListener(new Paragraph("Large Size: " + order.getLargeSize()));
            document.addDocumentListener(new Paragraph("Agreed Price: $" + order.getOrderAgreedPrice()));
            document.addDocumentListener(new Paragraph("Cloth Material: " + order.getClothMaterial()));
            document.addDocumentListener(new Paragraph("Order Status: " + order.getOrderStatus()));
            document.addDocumentListener(new Paragraph("Order Covered Amount: $" + order.getOrderCoveredAmount()));
            double totalAmount = order.getOrderAgreedPrice() *
                    (order.getSmallSize() + order.getMediumSize() + order.getLargeSize());
            document.addDocumentListener(new Paragraph("Total Amount: $" + totalAmount));

            document.removeDocumentListener((DocumentListener) document);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=order_report_" + OrderId + ".pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(inputStream));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }



    @PostMapping("/checkInventory")
    public ResponseEntity<OrderDTO> checkInventory(@RequestBody Order request) {
        boolean isInventorySufficient = orderService.checkInventory(
                request.getModelName(),
                request.getTotalSize(),
                request.getClothMaterial()
        );
        return ResponseEntity.ok(new OrderDTO(isInventorySufficient));
    }



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

//public class OrderStatusResponse {
//    private String OrderStatus;
//
//    public OrderStatusResponse(String OrderStatus) {
//        this.OrderStatus = OrderStatus;
//    }
//
//    public String getOrderStatus() {
//        return OrderStatus;
//    }
//
//    public void setOrderStatus(String OrderStatus) {
//        this.OrderStatus = OrderStatus;
//    }
//}
