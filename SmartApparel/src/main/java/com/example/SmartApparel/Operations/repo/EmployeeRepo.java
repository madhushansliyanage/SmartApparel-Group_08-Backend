package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for managing Employee entities.
 */
public interface EmployeeRepo extends JpaRepository<Employee, String> {

    /**
     * Custom query to get the position of an employee by their ID.
     *
     * @param empId The ID of the employee.
     * @return The position of the employee.
     */
    @Query(value = "SELECT position FROM employee WHERE emp_id=?1", nativeQuery = true)
    String getEmployeePosition(String empId);

    /**
     * Custom query to get all employee IDs.
     *
     * @return A list of all employee IDs.
     */
    @Query(value = "SELECT emp_id FROM employee", nativeQuery = true)
    List<String> getAllEmployeeId();
}
