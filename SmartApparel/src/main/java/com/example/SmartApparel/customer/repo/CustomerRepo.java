package com.example.customerAMS.repo;

import com.example.customerAMS.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository <Customer,Integer>{
}
