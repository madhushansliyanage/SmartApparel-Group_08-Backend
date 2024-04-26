package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.OrderDTO;
import com.example.SmartApparel.Operations.entity.OrderInventoryItem;
import com.example.SmartApparel.Operations.repo.OrderInventoryRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInventoryService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderInventoryRepository inventoryRepository;

    public boolean checkSufficientMaterials(Order order) {
        // Check if there is sufficient materials in inventory for the order
        try {
            if (order.getQuantity() <= getAvailableQuantity) {
                //return ResponseEntity.ok("Sufficient materials in inventory");
                return true;
            } else {
                //return ResponseEntity.badRequest().body("Insufficient materials in inventory");
                return false;
            }
        }
        //return inventoryRepository.checkAvailability(order);
    }

    public void allocateMaterials(Order order) {
        boolean quantity = checkSufficientMaterials(order);
        // Logic to allocate materials for the order and update its status
        if (quantity) {
            // Allocate materials and update order status
            allocateMaterials(order);
        } else {
            // Handle case when there are insufficient materials
            return ResponseEntity.badRequest().body("Insufficient materials in inventory");
        }

        // Update order status to "Allocated" after allocating materials
        order.setStatus("Allocated");
        // Save the updated order status to the database
        orderRepository.save(order);

        //return inventoryRepository.allocateMaterials(order);
    }
}


