package com.sai.project.controller;

import java.util.List; 
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sai.project.expection.EmployeeNotFoundException;
import com.sai.project.models.Employee;
import com.sai.project.service.EmployeeService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v1")

@CrossOrigin(origins ="*")

public class EmployeeController {
   @Autowired   
   EmployeeService employeeService;
	
    
	 @PostMapping("/saveemployee")
	 public ResponseEntity <Employee>  saveEmployee(@RequestBody @Valid Employee employee){
		 
		 Employee saveEmp = employeeService.saveEmployee(employee);
		 
		 return  ResponseEntity.status(HttpStatus.CREATED)
				                .header("employee status", "employee saved successfully")
				                .body(saveEmp);
	 }
	 
	 
	 
	 @PostMapping("/saveall")
	 public ResponseEntity <List<Employee>> saveAllEmployees(@RequestBody List<Employee> employees){
		 
		 List<Employee> saveEmpls = employeeService.saveAllEmployees(employees);
		 
		 return  ResponseEntity.status(HttpStatus.CREATED)
				                .header("employee status", "employee saved successfully")
				                .body(saveEmpls);
	 }
	 
	 
	 
	 
	 /*@GetMapping("/getall")
	 
	 public ResponseEntity <List<Employee>>  getAllEmployees(){
		 
		 List<Employee> emps = employeeService.getAllEmployees();
		 
		 return ResponseEntity.status(HttpStatus.OK)
				              .header("status", "data reading is done")
				              .body(emps);
		 
	 } */
	 
	 
	 @GetMapping("/getall")
	 public ResponseEntity<?> getAllEmployees() {
	     List<Employee> employees = employeeService.getAllEmployees();  // Assuming employeeService provides a list of employees

	     if (!employees.isEmpty()) {
	         // Convert the list of employees to a list of EntityModels
	         List<EntityModel<Employee>> employeeModels = employees.stream()
	                 .map(employee -> {
	                     EntityModel<Employee> entityModel = EntityModel.of(employee);

	                     // Add self link for each employee
	                     entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).getById(employee.getId())).withSelfRel());

	                     // Add link to update the employee
	                     entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).updateEmployee(employee.getId(), employee)).withRel("update"));

	                     // Add link to delete the employee
	                     entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).deleteEmployeeById(employee.getId())).withRel("delete"));

	                     return entityModel;
	                 })
	                 .collect(Collectors.toList());

	         // Create a CollectionModel with the list of EntityModels
	         CollectionModel<EntityModel<Employee>> collectionModel = CollectionModel.of(employeeModels);

	         // Add a link to the collection itself
	         collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());

	         return ResponseEntity.status(HttpStatus.OK)
	                 .body(collectionModel);
	     } else {
	         throw new EmployeeNotFoundException("No employees found");
	     }
	 }

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
    /* @GetMapping("/getbyid/{id}")
	 
	 public ResponseEntity <?>  getById(@PathVariable Long id ){
		 
		 Optional<Employee> optionalEmp = employeeService.getById(id);
		 
		 if(optionalEmp.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK)
						              .body(optionalEmp);
			}
		 
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						             .body("Emp is not found with id"+id);
			}	 
		 
	 }   */
	 
	 @GetMapping("/getbyid/{id}")
		public ResponseEntity<?> getById(@PathVariable Long id)
		{	Optional<Employee> optionalEmp = employeeService.getById(id);
			if(optionalEmp.isPresent())
			{	
				Employee employee = optionalEmp.get();
				 // Create an EntityModel for the user
		        EntityModel<Employee> entityModel = EntityModel.of(employee);

		        // Add self link
		        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).getById(id)).withSelfRel());

		        // Add link to update the user
		        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).updateEmployee(id, employee)).withRel("update"));

		        // Add link to delete the user
		        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).deleteEmployeeById(id)).withRel("delete"));

		        // Add link to get all users
		        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).getAllEmployees()).withRel("all-users"));
				
				return ResponseEntity.status(HttpStatus.OK)
									 .body(entityModel);
			}
			else
			{	//return ResponseEntity.status(HttpStatus.NOT_FOUND)
					//				 .body("Emp is not found with Id.."+id);	
				throw new EmployeeNotFoundException("Employee not found with id "+id);
			}
		}
	 
			
     
     
   
	 
	 
	 
	 
     
	 
 @GetMapping("/getbyemail/{email}")
 
 public ResponseEntity <?>  getByEmail(@PathVariable String email ){
	 
	 Optional<Employee> optionalEmp = employeeService.getByEmail(email);
	 
	 if(optionalEmp.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
					              .body(optionalEmp);
		}
	 
		else {
			
			// negative -1
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					             .body("Emp is not found with email"+email);
			
			// negative 2  is throwing the expextion 
			
			 	throw new EmployeeNotFoundException("Employee not Foung with email"+email);
			 	
				// negative 3 handling response 
			 	
			 	
			
		}	 
	 
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 // delete by id 
 @DeleteMapping("/delete/{id}")
 
 public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
	 
	 boolean status = employeeService.deleteEmployeeById(id);
	 
	 
	 if(status) {
		 
		 return  ResponseEntity.noContent().build();
	 }
	 else {
		 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND)
				              .header("status", "status not found")
				              .body("id is not found"+id);
	 }
 }
 
 
 
 // delete by email
 @DeleteMapping("/deletebyemail/{email}")
 
 public ResponseEntity<?> deleteEmployeeByEmail(@PathVariable String email) {
	 
	 boolean status = employeeService.deleteEmployeeByEmail(email);
	 
	 
	 if(status) {
		 
		 return  ResponseEntity.noContent().build();
	 }
	 else {
		 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND)
				              .header("status", "status not found")
				              .body("id is not found"+email);
	 }
	 
 }
	 
	
 
 // delete all 
 @DeleteMapping("/deleteall")
 
 public ResponseEntity<?> deleteEmployeeAll() {
	 
	 boolean status = employeeService.deleteEmployeeAll();
	 
	 
	 if(status) {
		 
		 return  ResponseEntity.noContent().build();
	 }
	 else {
		 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND)
				              .header("status", "status not found")
				              .body("data  is not found");
	 }
 }
 
 
 
 
 
 
 // update the existing data 
 @PutMapping("/update/{id}")
 
 public ResponseEntity<?> updateEmployee(@PathVariable Long id,@RequestBody Employee newEmployee){
	 
	 Optional<Employee> emps = employeeService.updateEmployee(id,newEmployee);
	 
	 if(emps.isPresent()) {
		 
		 return ResponseEntity.status(HttpStatus.OK)
	              .body(emps);
	 }
	 
	 else {
		 return ResponseEntity.status(HttpStatus.NOT_FOUND)
	              .header("status", "status not updated ")
	              .body("id is not found "+id);
		 
	 }
 }
 
 
 
 
 
 
 
 
 
 
 
       
 @PatchMapping("/update/{id}")
 
 public ResponseEntity<?> updateEmploye(@PathVariable Long id,@RequestBody Map<String,Object> updates){
	 
	 Optional<Employee> emps = employeeService.updateEmploye(id,updates);
	 
	 if(emps.isPresent()) {
		 
		 return ResponseEntity.status(HttpStatus.OK)
	              .body(emps); 
	 }
	 
	 else {
		 return ResponseEntity.status(HttpStatus.NOT_FOUND)
	              .header("status", "status not updated ")
	              .body("id is not found "+id);
		 
	 }
 }

 
 @GetMapping("/getallnames")
	public List<String> getAllNames(){
		return employeeService.getAllNames();
	}
 
 
 
 
	 
}













