package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepo extends JpaRepository<Inventory,Integer> {


}

