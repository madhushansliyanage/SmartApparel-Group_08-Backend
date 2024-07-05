package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing Salary entities.
 */
public interface SalaryRepo extends JpaRepository<Salary, Integer> {

    /**
     * Custom query to get the count of calculated salaries for a specific employee and year-month.
     *
     * @param empId      The ID of the employee.
     * @param yearNMonth The year and month for which the salary is calculated (in the format YYYY-MM).
     * @return The count of calculated salaries for the given employee and year-month.
     */
    @Query(value = "SELECT COUNT(salary_id) FROM salary WHERE emp_id=?1 AND yearnmonth=?2", nativeQuery = true)
    int getCalculatedSalaryCount(String empId, String yearNMonth);

    @Query(value = "SELECT salary_id FROM salary WHERE emp_id=?1 AND yearnmonth=?2", nativeQuery = true)
    int getSalaryIdByEmpIdAndYearNMonth(String empId, String yearNMonth);
}
