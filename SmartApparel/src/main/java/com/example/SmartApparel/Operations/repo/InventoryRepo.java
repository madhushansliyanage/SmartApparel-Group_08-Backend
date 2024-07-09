package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Inventory; // Importing the Inventory entity class.
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository interface.
import org.springframework.data.jpa.repository.Modifying; // Importing Modifying annotation for update/delete queries.
import org.springframework.data.jpa.repository.Query; // Importing Query annotation to define custom queries.
import org.springframework.data.repository.query.Param; // Importing Param annotation to pass parameters in queries.

// Interface definition: InventoryRepo interface extending JpaRepository to provide CRUD operations for Inventory entity.
public interface InventoryRepo extends JpaRepository<Inventory,Integer> {


}

