package com.example.SmartApparel.customer.repo;

import com.example.SmartApparel.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository <Customer,Integer>{
}
