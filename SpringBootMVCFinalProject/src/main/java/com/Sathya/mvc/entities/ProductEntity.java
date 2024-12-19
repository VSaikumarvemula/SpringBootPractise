package com.Sathya.mvc.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "PRODUCTS")
public class ProductEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long proId;
	
	
	private String proName;
	private double proPrice;
	private String proBrand;
	private String proDescription;
	
	private double dprice;
	private String proCategory;
	
	private LocalDate createdAt;
	private String createdBy;
	
	
	
	
	

}
