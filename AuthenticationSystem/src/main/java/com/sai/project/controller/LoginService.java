package com.sai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.project.entity.LoginEntity;
import com.sai.project.models.LoginModel;


@Service


public class LoginService {
	
	
	@Autowired
	LoginRepository loginRepository;

	public void saveUser(LoginModel user) {
		
		LoginEntity entity = new LoginEntity();
		
		    entity.setFirstName(user.getFirstName());
		    entity.setLastName(user.getLastName());
	        entity.setEmail(user.getEmail());
	        entity.setMobile(user.getMobile());
	        entity.setUsername(user.getUsername()); 
	        entity.setPassword(user.getPassword());
	        entity.setCity(user.getCity());
	        entity.setState(user.getState());
	        entity.setCountry(user.getCountry());
		
		 loginRepository.save(entity);	
	}
	
	
	
	
	
	

	public boolean details(String username, String password) {
	    LoginEntity obj = loginRepository.findByUsername(username);
	    
	    if (obj != null && obj.getUsername().equals(username) && obj.getPassword().equals(password)) {
	        return true;
	    } 
	    else {
	        return false;
	    }
	}

	
	
	
	

}
