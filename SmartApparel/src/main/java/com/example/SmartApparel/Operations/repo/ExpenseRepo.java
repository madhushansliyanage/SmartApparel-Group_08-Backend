package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense,Integer> {
}
