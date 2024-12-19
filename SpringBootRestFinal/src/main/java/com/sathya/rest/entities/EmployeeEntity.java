package com.sathya.rest.entities;

import java.time.LocalDate; 

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long empId;
private String empName;
private double basicSalary;
private double hra;
private double da;
private double pf;
private double totalSalary;

private String empDept;
private String empEmail;
private Long empMobile;

private LocalDate joinDate;
	
}


