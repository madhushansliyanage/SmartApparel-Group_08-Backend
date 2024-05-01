package com.example.SmartApparel.Operations.service;

import com.example.SmartApparel.Operations.dto.SalaryDTO;
import com.example.SmartApparel.Operations.entity.Salary;
import com.example.SmartApparel.Operations.entity.SalaryParameter;
import com.example.SmartApparel.Operations.repo.AttendanceRepo;
import com.example.SmartApparel.Operations.repo.EmployeeRepo;
import com.example.SmartApparel.Operations.repo.SalaryParameterRepo;
import com.example.SmartApparel.Operations.repo.SalaryRepo;
import com.example.SmartApparel.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SalaryService {
    @Autowired
    private SalaryRepo salaryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private SalaryParameterRepo salaryParameterRepo;

    public List<SalaryDTO> viewAllSalary(){

        List<Salary> salaryList = salaryRepo.findAll();

        return modelMapper.map(salaryList,new TypeToken<List<SalaryDTO>>(){}.getType());
    }

    public SalaryDTO searchSalaryByID(int salaryId){

        if (salaryRepo.existsById(salaryId)){

            Salary salary = salaryRepo.findById(salaryId).orElse(null);
            return modelMapper.map(salary,SalaryDTO.class);
        }else{

            return null;
        }
    }

    public String addNewSalary(SalaryDTO salaryDTO){

        if(salaryRepo.getCalculatedSalaryCount(salaryDTO.getEmpId(),salaryDTO.getYearNMonth())>=1){

            return VarList.RSP_DUPLICATED;
        }else{

            salaryRepo.save(modelMapper.map(salaryDTO, Salary.class));

            return VarList.RSP_SUCCESS;
        }
    }

    public String updateSalary(SalaryDTO salaryDTO){

        if(salaryRepo.existsById(salaryDTO.getSalaryId())){

            salaryRepo.save(modelMapper.map(salaryDTO,Salary.class));

            return VarList.RSP_SUCCESS;
        }else{

            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String deleteSalaryByID(int salaryId){

        if (salaryRepo.existsById(salaryId)){

            salaryRepo.deleteById(salaryId);
            System.out.println("inside delete service method");

            return VarList.RSP_SUCCESS;
        }else{

            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String calculateSalary(String empId,String yearMonth){

        int attCount = attendanceRepo.getAttendanceCount(yearMonth, empId);
        String employeePosition = employeeRepo.getEmployeePosition(empId);
        SalaryParameter salaryParameter = salaryParameterRepo.searchSalaryParamByPosition(employeePosition);

        float basicForMonth = (salaryParameter.getBasicSalary() / 26) * attCount;
        float epfByEmployee = (basicForMonth * salaryParameter.getEpfByEmployee()) / 100;
        float epfByCompany = (basicForMonth * salaryParameter.getEpfByCompany()) / 100;
        float etfPayment = (basicForMonth * salaryParameter.getEtf()) / 100;
        float netSalary = basicForMonth - epfByEmployee;

//        System.out.println("attCount: " + attCount);
//        System.out.println("employeePosition: " + employeePosition);
//        System.out.println("salaryParms: " + salaryParameter);
//        System.out.println("basicForMonth: " + basicForMonth);
//        System.out.println("epfByEmployee: " + epfByEmployee);
//        System.out.println("epfByCompany: " + epfByCompany);
//        System.out.println("etfPayment: " + etfPayment);
//        System.out.println("netSalary: " + netSalary);

        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setEmpId(empId);
        salaryDTO.setStatus("Pending");
        salaryDTO.setYearNMonth(yearMonth);
        salaryDTO.setBasic(basicForMonth);
        salaryDTO.setEpfByEmployee((float) (Math.ceil(epfByEmployee * 100) / 100));
        salaryDTO.setEpfByCompany((float) (Math.ceil(epfByCompany * 100) / 100));
        salaryDTO.setEtfPayment((float) (Math.ceil(etfPayment * 100) / 100));
        salaryDTO.setNetSalary((float) (Math.ceil(netSalary * 100) / 100));

        return addNewSalary(salaryDTO);
    }

    public String  calculateSalaryFroAll(String yearMonth){

        List<String> stringList = employeeRepo.getAllEmployeeId();
        int successCount=0;

        System.out.println("########################");
        stringList.forEach(id -> System.out.println(id));
        System.out.println("########################");

        for(String id : stringList){
            if(VarList.RSP_SUCCESS == calculateSalary(id,yearMonth)){
                successCount++;
            }
        }
        if (successCount>0){
            return VarList.RSP_SUCCESS;
        }

        return VarList.RSP_DUPLICATED;
    }
}
