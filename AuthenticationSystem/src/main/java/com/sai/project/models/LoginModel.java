package com.sai.project.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {
	
	private String firstName ;
	private String lastName ;
	private String email ;
	private Long mobile ;
	private String Username;
	private String password;
	private String city;
	private String state;
	private String country;
	
	

}
