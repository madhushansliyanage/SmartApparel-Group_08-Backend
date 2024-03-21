package com.example.SmartApparel.customer.repo;

import com.example.SmartApparel.customer.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense,Integer> {
}
