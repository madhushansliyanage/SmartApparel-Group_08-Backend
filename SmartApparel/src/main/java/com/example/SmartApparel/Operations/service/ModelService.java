package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.ModelDTO;
import com.example.SmartApparel.Operations.entity.Model;
import com.example.SmartApparel.Operations.repo.ModelRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ModelService {

    // Autowired CustomerRepo and ModelMapper
    @Autowired
    private ModelRepo modelRepo;
    @Autowired
    private ModelMapper modelMapper;

    // Method to save a new model
    public String saveModel(ModelDTO modelDTO){
        // Check if a model with the given name already exists
        if (modelRepo.existsById(modelDTO.getModelName())){
            return VarList.RSP_DUPLICATED;
        }else {
            // Save the model to the database
            modelRepo.save(modelMapper.map(modelDTO, Model.class));
            return VarList.RSP_SUCCESS;
        }
    }
}
