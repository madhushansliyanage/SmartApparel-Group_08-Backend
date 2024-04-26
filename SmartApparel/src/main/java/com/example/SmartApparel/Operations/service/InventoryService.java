package com.example.SmartApparel.Operations.service;


import com.example.SmartApparel.Operations.dto.ExpenseDTO;
import com.example.SmartApparel.Operations.dto.InventoryDTO;
import com.example.SmartApparel.Operations.entity.Expense;
import com.example.SmartApparel.Operations.entity.Inventory;
import com.example.SmartApparel.Operations.repo.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Inventory addNewItem(Inventory inventory) throws Exception{
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllItems() throws  Exception{
        return inventoryRepository.findAll();
    }

    public Inventory updateItem(Inventory inventory) throws  Exception{
        return inventoryRepository.save(inventory);
    }

    public String deleteItem(int itemID) throws Exception{
        inventoryRepository.deleteById(itemID);
        return "Delete Successfully";
    }

    public Optional<Inventory> getItemById(int itemID) throws Exception{
        return inventoryRepository.findById(itemID);
    }

}
