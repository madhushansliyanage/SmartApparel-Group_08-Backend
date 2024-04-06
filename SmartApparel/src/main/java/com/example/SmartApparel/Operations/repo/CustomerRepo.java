package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository <Customer,Integer>{
}
