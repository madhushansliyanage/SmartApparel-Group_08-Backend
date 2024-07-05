package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository <Customer,Integer>{
    Optional<Customer> findById(Integer CustomerId);
}