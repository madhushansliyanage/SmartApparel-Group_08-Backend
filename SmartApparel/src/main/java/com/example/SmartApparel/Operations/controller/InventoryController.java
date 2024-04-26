package com.example.SmartApparel.Operations.controller;


import com.example.SmartApparel.Operations.dto.ExpenseDTO;
import com.example.SmartApparel.Operations.dto.InventoryDTO;
import com.example.SmartApparel.Operations.dto.ResponseDTO;
import com.example.SmartApparel.Operations.entity.Inventory;
import com.example.SmartApparel.Operations.service.InventoryService;
import com.example.SmartApparel.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "smart-apperal/api/inventories")
//Connect with Frontend
@CrossOrigin
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ResponseDTO responseDTO;

    //Add new inventory item
    @PostMapping(path = "/addItem")
    public ResponseEntity<Inventory> addNewItem(@RequestBody Inventory inventory)throws  Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.addNewItem(inventory));
    }

    //View all inventory items
    @GetMapping(path = "/items")
    public ResponseEntity<List<Inventory>> getAllItems() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getAllItems());
    }


    //Update item
    @PutMapping(path = "/updateItem")
    public ResponseEntity<Inventory> updateItem( @RequestBody Inventory inventory)throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.updateItem(inventory));
    }

    //Delete Item
    @DeleteMapping(path = "/deleteItem/{itemID}")
    public ResponseEntity<String> deleteItem(@PathVariable int itemID)throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.deleteItem(itemID));
    }

    //Get item by id
    @GetMapping(path = "/item/{itemID}")
    public ResponseEntity<Optional<Inventory>> getItemById(@PathVariable int itemID) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getItemById(itemID));
    }
}
