package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Order, int> {

    boolean checkSufficientMaterials(Order order);

    void allocateMaterials(Order order);
}


