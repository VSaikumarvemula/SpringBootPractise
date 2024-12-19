package com.sathya.rest.models;



import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
	
	
	private String empName;
	private double basicSalary;
	

	private String empDept;
	private String empEmail;
	private Long empMobile;

	

}
