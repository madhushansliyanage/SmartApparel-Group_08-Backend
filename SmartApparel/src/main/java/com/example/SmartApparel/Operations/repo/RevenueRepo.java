package com.example.SmartApparel.Operations.repo;


import com.example.SmartApparel.Operations.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RevenueRepo extends JpaRepository<Revenue,Integer> {
    @Query(value = "SELECT SUM(amount) FROM revenue", nativeQuery = true)
    double getTotalRevenueSum();

}
