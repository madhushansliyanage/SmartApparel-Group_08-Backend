package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.SalaryParameterDTO;
import com.example.SmartApparel.Operations.entity.SalaryParameter;
import com.example.SmartApparel.Operations.repo.SalaryParameterRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling Salary Parameters.
 */
@Service
@Transactional
public class SalaryParameterService {

    /**
     * Repository for managing SalaryParameter entities.
     */
    @Autowired
    private SalaryParameterRepo salaryParameterRepo;

    /**
     * ModelMapper for mapping between entities and DTOs.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all Salary Parameters from the SalaryParameter table.
     *
     * @return List of SalaryParameterDTOs representing the retrieved data.
     */
    public List<SalaryParameterDTO> viewSalaryParams() {
        // Retrieve all Salary Parameters from the repository
        List<SalaryParameter> salaryParameterList = salaryParameterRepo.findAll();

        // Convert to DTOs and return
        return modelMapper.map(salaryParameterList, new TypeToken<List<SalaryParameterDTO>>(){}.getType());
    }


    /**
     * Searches and returns Salary Parameters by their ID from the SalaryParameters table.
     *
     * @param spID The ID of the Salary Parameter to search for.
     * @return SalaryParameterDTO representing the retrieved data, or null if not found.
     */
    public SalaryParameterDTO searchSalaryParamsByID(int spID) {
        // Check if Salary Parameters exists
        if (salaryParameterRepo.existsById(spID)) {
            // Retrieve Salary Parameters from repository and map to DTO
            SalaryParameter salaryParameter = salaryParameterRepo.findById(spID).orElse(null);
            return modelMapper.map(salaryParameter, SalaryParameterDTO.class);
        } else {
            // Return null if Salary Parameters not found
            return null;
        }
    }

    /**
     * Searches for salary parameters based on the specified position.
     *
     * @param position The position for which to retrieve salary parameters.
     * @return A {@link SalaryParameterDTO} containing the mapped salary parameters,
     *         or {@code null} if no parameters are found.
     */
    public SalaryParameterDTO searchSalaryParamsByPosition(String position) {
        // Check if Salary Parameters exist
        SalaryParameter salaryParameter = salaryParameterRepo.searchSalaryParamByPosition(position);
        if (salaryParameter != null) {
            return modelMapper.map(salaryParameter, SalaryParameterDTO.class);
        } else {
            // Return null if Salary Parameters are not found
            return null;
        }
    }


    /**
     * Method to save a new Salary Parameters in the SalaryParameters table
     * @param salaryParameterDTO The SalaryParameterDTO to be saved
     * @return Response indicating success or duplication
     */
    public String addNewSalaryParam(SalaryParameterDTO salaryParameterDTO){

        // Check if Salary Parameter Position already exists
        SalaryParameterDTO fetchedSalaryParameterDTO = searchSalaryParamsByPosition(salaryParameterDTO.getPosition());

        if(fetchedSalaryParameterDTO!=null){
            // Return response indicating duplication
            return VarList.RSP_DUPLICATED;
        }else{
            // Save new Salary Parameter to repository
            salaryParameterRepo.save(modelMapper.map(salaryParameterDTO, SalaryParameter.class));
            // Return success response
            return VarList.RSP_SUCCESS;
        }
    }

    /**
     * Updates a SalaryParams record in the SalaryParameters table.
     *
     * @param salaryParameterDTO The SalaryParameterDTO containing the updated data.
     * @return A string indicating the operation's success or failure.
     */
    public String updateSalaryParam(SalaryParameterDTO salaryParameterDTO) {
        // Check if SalaryParams exists
        if (salaryParameterRepo.existsById(salaryParameterDTO.getSalaryParameterId())) {
            // Update SalaryParams in repository
            salaryParameterRepo.save(modelMapper.map(salaryParameterDTO, SalaryParameter.class));
            // Return success response
            return VarList.RSP_SUCCESS;
        } else {
            // Return response indicating SalaryParams not found
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    /**
     * Deletes a SalaryParams record from the SalaryParams table.
     *
     * @param salaryParameterId The ID of the SalaryParams to be deleted.
     * @return A string indicating the operation's success or failure.
     */
    public String deleteSalaryParamByID(int salaryParameterId) {
        // Check if SalaryParams exists
        if (salaryParameterRepo.existsById(salaryParameterId)) {
            // Delete SalaryParams from repository
            salaryParameterRepo.deleteById(salaryParameterId);
            // Return success response
            return VarList.RSP_SUCCESS;
        } else {
            // Return response indicating SalaryParams not found
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

}
