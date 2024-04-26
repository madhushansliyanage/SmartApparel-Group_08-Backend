package com.example.SmartApparel.Operations.controller;

import com.example.SmartApparel.Operations.dto.CustomerDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.service.CustomerService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/check")
    public ResponseEntity<String> checkInventory(@RequestBody Order order) {
        boolean isSufficient = inventoryService.checkSufficientMaterials(order);
        if (isSufficient) {
            // Allocate materials and set order status
            inventoryService.allocateMaterials(order);
            return ResponseEntity.ok("Materials allocated successfully");
        } else {
            return ResponseEntity.badRequest().body("Insufficient materials in inventory");
        }
    }
}
