package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.SalaryParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing SalaryParameter entities.
 */
public interface SalaryParameterRepo extends JpaRepository<SalaryParameter, Integer> {

    /**
     * Custom query to search for a salary parameter by position.
     *
     * @param position The job position for which the salary parameter is searched.
     * @return The salary parameter associated with the specified position.
     */
    @Query(value = "SELECT * FROM Salary_Parameter WHERE position=?1", nativeQuery = true)
    SalaryParameter searchSalaryParamByPosition(String position);
}
