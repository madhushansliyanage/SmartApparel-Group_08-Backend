package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository <Order,Integer>{
}
