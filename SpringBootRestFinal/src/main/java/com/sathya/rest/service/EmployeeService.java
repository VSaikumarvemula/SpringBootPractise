package com.sathya.rest.service;

import java.time.LocalDate; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.rest.entities.EmployeeEntity;
import com.sathya.rest.models.EmployeeModel;
import com.sathya.rest.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository  employeeRepository;

	public EmployeeEntity saveEmp(EmployeeModel model) {
		
		EmployeeEntity entity = new EmployeeEntity();

        // Set the fields from model to entity
        entity.setEmpName(model.getEmpName());
        entity.setBasicSalary(model.getBasicSalary());
        entity.setEmpDept(model.getEmpDept());
        entity.setEmpEmail(model.getEmpEmail());
        entity.setEmpMobile(model.getEmpMobile());

        // Calculate derived fields (like hra, da, pf, and totalSalary)
        double hra = model.getBasicSalary() * 0.10; // Example calculation
        double da = model.getBasicSalary() * 0.05;  // Example calculation
        double pf = model.getBasicSalary() * 0.12;  // Example calculation
        double totalSalary = model.getBasicSalary() + hra + da - pf;

        entity.setHra(hra);
        entity.setDa(da);
        entity.setPf(pf);
        entity.setTotalSalary(totalSalary);

        // Set other fields like joinDate if applicable
        entity.setJoinDate(LocalDate.now());
       
         return  employeeRepository.save(entity);
         
		
	}
	

}
