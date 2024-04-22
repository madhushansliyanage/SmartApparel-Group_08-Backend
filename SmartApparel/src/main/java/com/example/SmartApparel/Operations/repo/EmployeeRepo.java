package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for Employee entity, extends JpaRepository
public interface EmployeeRepo extends JpaRepository<Employee,String> {

}
