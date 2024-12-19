package com.sai.project.entity;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@Entity 
public class LoginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long proId;
	private String firstName ;
	private String lastName ;
	private String email ;
	private Long mobile ;
	private String username;
	private String password;
	private String city;
	private String state;
	private String country;
}
