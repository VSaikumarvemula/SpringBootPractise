package com.sai.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class Controller {
	
	@Autowired 
	RestTemplate restTemplate;
	  
	@GetMapping("/pincode/{pincode}")
	
	public  ResponseEntity<?> getPostedDetails(@PathVariable String pincode){
		
		// create the object for RestTemplate to call the service 
		
		//RestTemplate restTemplate = new RestTemplate();
		
		// call the service & store the result 
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://api.postalpincode.in/pincode/"+pincode, String.class);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(response.getBody());
		
	}
	
	
	@GetMapping("/ratansirquotes")
	
     public ResponseEntity<String> getQuotes(){
		// create the object for RestTemplate to call the service 
		
				//RestTemplate restTemplate = new RestTemplate();
				
				// call the service & store the result 
				
				ResponseEntity<String> response = restTemplate.getForEntity("http://api.quotable.io/random", String.class);
				
				return ResponseEntity.status(HttpStatus.OK)
						.body(response.getBody());	
		
	}
 
}
