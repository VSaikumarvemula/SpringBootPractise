package com.sai.project.service;

import java.util.List; 
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import com.sai.project.models.Employee;
import com.sai.project.repository.EmployeeRepository;



@Service
public class EmployeeService {

@Autowired
EmployeeRepository    employeeRepository ;
	
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	public List<Employee> saveAllEmployees(List<Employee> employees) {
		
		return  employeeRepository.saveAll(employees);
	}

	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

	
	public Optional<Employee> getById(Long id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		return  emp;
	}
	
	

	public Optional<Employee> getByEmail(String email) {
		
		return employeeRepository.findByEmail(email);
	}
	
	
	
	
	

	public boolean deleteEmployeeById(Long id) {
	      
		 boolean status = employeeRepository.existsById(id);
		 
		 if(status) {
			 
			 employeeRepository.deleteById(id);
		  return true;
		  
		 }
		 else {
			 return false;
		 }
	}
	

	
	
	
	
	public boolean deleteEmployeeByEmail(String email) {
		
		boolean status = employeeRepository.existsByEmail(email);
		
          if(status) {
			 
			 employeeRepository.deleteByEmail(email);
		       return true;
		  
		 }
		 else {
			 return false;
		 }
	}
	
	
	
	
	

	public boolean deleteEmployeeAll() {
		
		boolean status = employeeRepository.count() > 0 ;
		
		if(status) {
			
			employeeRepository.deleteAll();
			return true;
		}
		else {
			 return false;
		 }
	}
	
	

	public Optional<Employee> updateEmployee(Long id, Employee newEmployee) {
		
		 Optional<Employee> existingData = employeeRepository.findById(id);
		
		 if(existingData.isPresent()) {
			 Employee existingEmployee = existingData.get();
			 
			 existingEmployee.setName(newEmployee.getName());
			 
			 existingEmployee.setSalary(newEmployee.getSalary());
			 
			 existingEmployee.setAddress(newEmployee.getAddress());
			 existingEmployee.setDepartment(newEmployee.getDepartment());
			 existingEmployee.setEmail(newEmployee.getEmail());
			 
			 Employee updatedEmployee = employeeRepository.save(existingEmployee);
			 
			 return Optional.of(updatedEmployee);
			 
		 }
		 
		 else {
			 
			 return Optional.empty();   
		 }
	}

	
	
	
	
	
	// patch mwthod specific update 
	public Optional<Employee> updateEmploye(Long id, Map<String, Object> updates) {
	   
	    Optional<Employee> optionalEmp = employeeRepository.findById(id);

	  
	    if (optionalEmp.isPresent()) {
	        Employee existingData = optionalEmp.get();

	        updates.forEach((key, value) -> {
	            switch (key) {
	                case "name":
	                    existingData.setName((String) value);
	                    break;
	                case "department":
	                    existingData.setDepartment((String) value);
	                    break;
	                case "salary":
	                    existingData.setSalary((Double) value);
	                    break;
	                case "address":
	                    existingData.setAddress((String) value);
	                    break;
	                case "email":
	                    existingData.setEmail((String) value);
	                    break;
	               
	            }
	        });

	        // Save the updated employee data to the database
	        employeeRepository.save(existingData);

	        return Optional.of(existingData);
	        
	    } 
	    
	    else {
	        // If employee not found, return Optional.empty()
	        return Optional.empty();
	    }
	    
	    
	}

	
	@Cacheable("names")
	
	public List<String> getAllNames() {
		
		System.out.println("fetching the names");
		return List.of("ravi","teja","Naidu");
	}
	
	
	
	}
	

	
	


