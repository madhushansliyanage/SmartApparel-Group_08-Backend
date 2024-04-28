package com.example.SmartApparel.Operations.repo;

import com.example.SmartApparel.Operations.entity.Attendance;
import com.example.SmartApparel.Operations.entity.SalaryParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * Repository interface for managing SalaryParameter entities.
 */
public interface SalaryParameterRepo extends JpaRepository<SalaryParameter, Integer> {

    @Query(value = "SELECT * FROM Salary_Parameter WHERE position=?1",nativeQuery = true)
    SalaryParameter searchSalaryParamByPosition(String position);
}
