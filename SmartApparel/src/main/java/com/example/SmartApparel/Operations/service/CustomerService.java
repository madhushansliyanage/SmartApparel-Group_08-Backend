package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.CustomerDTO;
import com.example.SmartApparel.Operations.entity.Customer;
import com.example.SmartApparel.Operations.repo.CustomerRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    // Autowired CustomerRepo and ModelMapper
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Method to save a new customer
    public String saveCustomer(CustomerDTO customerDTO){
        // Check if a customer with the given ID already exists
        if (customerRepo.existsById(customerDTO.getCustomerId())){
            return VarList.RSP_Duplicate;
        }else {
            // Save the customer to the database
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_Success;
        }
    }

    // Method to update an existing customer
    public String updateCustomer(CustomerDTO customerDTO){
        // Check if a customer with the given ID exists
        if (customerRepo.existsById(customerDTO.getCustomerId())){
            // Update the customer in the database
            customerRepo.save(modelMapper.map(customerDTO,Customer.class));
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }

    // Method to retrieve all customers
    public List<CustomerDTO> viewCustomer(){
        // Retrieve all customers from the database
        List<Customer> customerList = customerRepo.findAll();
        // Map the list of entities to a list of DTOs
        return modelMapper.map(customerList, new TypeToken<ArrayList<CustomerDTO>>(){}.getType());
    }

    // Method to search for a customer by ID
    public CustomerDTO searchCustomer(int CustomerId){
        // Check if a customer with the given ID exists
        if (customerRepo.existsById(CustomerId)){
            // Retrieve the customer from the database
            Customer customer = customerRepo.findById(CustomerId).orElse(null);
            // Map the entity to a DTO and return
            return modelMapper.map(customer,CustomerDTO.class);
        }else {
            return null;
        }
    }

    // Method to delete a customer by ID
    public String deleteCustomer(int CustomerId){
        // Check if a customer with the given ID exists
        if (customerRepo.existsById(CustomerId)){
            // Delete the customer from the database
            customerRepo.deleteById(CustomerId);
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }
}
