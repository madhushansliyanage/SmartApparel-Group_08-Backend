package com.example.SmartApparel.hrmanage.repository;

import com.example.SmartApparel.hrmanage.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for Employee entity, extends JpaRepository
public interface EmployeeRepo extends JpaRepository<Employee,String> {

}
