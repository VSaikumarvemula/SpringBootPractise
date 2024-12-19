package com.sai.hospital.entity;

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

public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String  name;	
	private String location;
	private long mobile;
	private String specialization;
	private int rating;
	
	
}


