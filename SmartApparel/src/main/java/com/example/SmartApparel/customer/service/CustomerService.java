package com.example.customerAMS.service;

import com.example.customerAMS.dto.CustomerDTO;
import com.example.customerAMS.entity.Customer;
import com.example.customerAMS.repo.CustomerRepo;
import com.example.customerAMS.util.VarList;
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
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveCustomer(CustomerDTO customerDTO){
        if (customerRepo.existsById(customerDTO.getCustomerId())){
            return VarList.RSP_Duplicate;
        }else {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_Success;
        }
    }

    public String updateCustomer(CustomerDTO customerDTO){
        if (customerRepo.existsById(customerDTO.getCustomerId())){
            customerRepo.save(modelMapper.map(customerDTO,Customer.class));
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }

    public List<CustomerDTO> viewCustomer(){
        List<Customer> customerList = customerRepo.findAll();
        return modelMapper.map(customerList, new TypeToken<ArrayList<CustomerDTO>>(){}.getType());
    }

    public CustomerDTO searchCustomer(int CustomerId){ //need to search using customer name
        if (customerRepo.existsById(CustomerId)){
            Customer customer = customerRepo.findById(CustomerId).orElse(null);
            return modelMapper.map(customer,CustomerDTO.class);
        }else {
            return null;
        }
    }

    public String deleteCustomer(int CustomerId){
        if (customerRepo.existsById(CustomerId)){
            customerRepo.deleteById(CustomerId);
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_No_Data_Found;
        }
    }
}
