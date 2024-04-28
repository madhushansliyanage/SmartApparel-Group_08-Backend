package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaryRepo extends JpaRepository<Salary,Integer> {
    @Query(value = "SELECT COUNT(salary_id) FROM salary WHERE emp_id=?1 AND yearnmonth=?2", nativeQuery = true)
    int getSalaryCount(String empId,String yearNMonth);
}
