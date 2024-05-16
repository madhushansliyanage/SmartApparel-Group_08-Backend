package com.example.SmartApparel.Operations.repo;


import com.example.SmartApparel.Operations.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RevenueRepo extends JpaRepository<Revenue,Integer> {

    //Query for get total sum of revenue..................................................................................................
    @Query(value = "SELECT SUM(amount) FROM revenue", nativeQuery = true)
    double getTotalRevenueSum();

    //Query for get revenue details among date range .....................................................................................
    @Query(value = "SELECT * FROM revenue WHERE date BETWEEN 'start_date' AND 'end_date' ", nativeQuery = true)
    double getRevenueByDateRange(String start_date,String end_date);

    //Query for get revenue details among date range orderd by date .....................................................................................
    @Query(value = "SELECT * FROM revenue WHERE date BETWEEN 'start_date' AND 'end_date' order(date)", nativeQuery = true)
    double getRevenueByDateRangeOrderd(String start_date,String end_date);

}
