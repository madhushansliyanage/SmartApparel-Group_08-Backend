package com.example.SmartApparel.Operations.repo;


import com.example.SmartApparel.Operations.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
}