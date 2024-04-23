package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepo extends JpaRepository<Expense,Integer> {
    @Query(value = "SELECT SUM(amount) FROM expense", nativeQuery = true)
    double getTotalExpenseSum();
}
