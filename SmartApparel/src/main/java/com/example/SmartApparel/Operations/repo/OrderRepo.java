package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository <Order,Integer>{

    @Query(value = "SELECT order_id FROM orders where order_status='Completed'", nativeQuery = true)
    List<Integer> getCompletedOrders();
}
