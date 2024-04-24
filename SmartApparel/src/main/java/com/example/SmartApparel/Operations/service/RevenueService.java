package com.example.SmartApparel.Operations.service;


import com.example.SmartApparel.Operations.dto.ExpenseDTO;
import com.example.SmartApparel.Operations.dto.RevenueDTO;
import com.example.SmartApparel.Operations.entity.Revenue;
import com.example.SmartApparel.Operations.repo.RevenueRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class RevenueService {
    @Autowired
    private RevenueRepo revenueRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveRevenue(RevenueDTO revenueDTO){
        if (revenueRepo.existsById(revenueDTO.getRevenue_ID())){
            return VarList.RSP_DUPLICATED;
        }else {
            revenueRepo.save(modelMapper.map(revenueDTO, Revenue.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateRevenue(RevenueDTO revenueDTO){
        if (revenueRepo.existsById(revenueDTO.getRevenue_ID())){
            revenueRepo.save(modelMapper.map(revenueDTO,Revenue.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<RevenueDTO> viewRevenue(){
        List<Revenue> revenueList = revenueRepo.findAll();
        return modelMapper.map(revenueList, new TypeToken<ArrayList<ExpenseDTO>>(){}.getType());
    }

    public RevenueDTO searchRevenue(int RevenueId){ //need to search using customer name
        if (revenueRepo.existsById(RevenueId)){
            Revenue revenue = revenueRepo.findById(RevenueId).orElse(null);
            return modelMapper.map(revenue, RevenueDTO.class);
        }else {
            return null;
        }
    }

    public String deleteRevenue(int RevenueId){
        if (revenueRepo.existsById(RevenueId)){
            revenueRepo.deleteById(RevenueId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }


    // To get sum of the Revenue Received
    public double getTotalRevenueSum() {
        return revenueRepo.getTotalRevenueSum();
    }


}
