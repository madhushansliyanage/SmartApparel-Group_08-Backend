package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.EmployeeDTO;
import com.example.SmartApparel.Operations.entity.Employee;
import com.example.SmartApparel.Operations.repo.EmployeeRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Method to retrieve all employees from the employee table
    public List<EmployeeDTO> viewAllEmployees(){
        // Retrieve all employees from repository
        List<Employee> employeeList = employeeRepo.findAll();
        // Convert to DTOs and return
        return modelMapper.map(employeeList,new TypeToken<List<EmployeeDTO>>(){}.getType());
    }

    // Method to search and return an employee by their ID from the employee table
    public EmployeeDTO searchEmployeeByID(String empId){
        // Check if employee exists
        if (employeeRepo.existsById(empId)){
            // Retrieve employee from repository and map to DTO
            Employee employee = employeeRepo.findById(empId).orElse(null);
            return modelMapper.map(employee,EmployeeDTO.class);
        }else{
            // Return null if employee not found
            return null;
        }
    }

    // Method to save a new Employee in the employee table
    public String addNewEmployee(EmployeeDTO employeeDTO){
        // Check if employee ID already exists
        if(employeeRepo.existsById(employeeDTO.getEmpId())){
            // Return response indicating duplication
            return VarList.RSP_DUPLICATED;
        }else{
            // Save new employee to repository
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            // Return success response
            return VarList.RSP_SUCCESS;
        }
    }

    // Method to update an Employee in the employee table
    public String updateEmployee(EmployeeDTO employeeDTO){
        // Check if employee exists
        if(employeeRepo.existsById(employeeDTO.getEmpId())){
            // Update employee in repository
            employeeRepo.save(modelMapper.map(employeeDTO,Employee.class));
            // Return success response
            return VarList.RSP_SUCCESS;
        }else{
            // Return response indicating employee not found
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Method to delete an Employee from the employee table
    public String deleteEmployeeByID(String empId){
        // Check if employee exists
        if (employeeRepo.existsById(empId)){
            // Delete employee from repository
            employeeRepo.deleteById(empId);
            // Return success response
            return VarList.RSP_SUCCESS;
        }else{
            // Return response indicating employee not found
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

}
