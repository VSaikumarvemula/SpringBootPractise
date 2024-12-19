package com.sai.hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sai.hospital.entity.Hospital;
import com.sai.hospital.service.HospitalService;




@RestController
@RequestMapping("/api/v1")
public class HospitalController {
	@Autowired 
	HospitalService hospitalService;
	
	
	// save the Hospital object into data base
	 @PostMapping("/savehospital")
	 public ResponseEntity <Hospital>  saveHospital(@RequestBody Hospital hospital){
		 
		 Hospital saveHsp = hospitalService.saveHospital(hospital);
		 
		 return  ResponseEntity.status(HttpStatus.CREATED)
				                .header("hospital status", "hospital saved successfully")
				                .body(saveHsp);
	 }
	 
	 
	 
	// save multiple hospitals objects into data base 
	 
	@PostMapping("/savehospitals")
	
	public ResponseEntity <List<Hospital>>  saveHospitals(@RequestBody List<Hospital> hospitals){
		
            List<Hospital> saveHsps = hospitalService.saveHospitals(hospitals);
		 
		 return  ResponseEntity.status(HttpStatus.CREATED)
				                .header("hospital status", "hospitals  saved successfully")
				                .body(saveHsps);
		
	}
	 
	 
	
	
	
	
	// get all hospitals records 
      @GetMapping("/gethospitals")
	
	public ResponseEntity <List<Hospital>>  getHospitals(){
		
            List<Hospital> getHsps = hospitalService.getHospitals();
		 
		 return  ResponseEntity.status(HttpStatus.OK)
				                .header("Hospital status", " hospital successfully read ")
				                .body(getHsps);
	}
      
      
      
      
      
     
      @GetMapping("/gethospitalbyid/{id}")
 	 
 	 public ResponseEntity <?>  getHospitalById(@PathVariable Long id ){
 		 
 		 Optional<Hospital> optionalHsp = hospitalService.getHospitalById(id);
 		 
 		 if(optionalHsp.isPresent()) {
 				return ResponseEntity.status(HttpStatus.OK)
 						              .body(optionalHsp.get());
 			}
 		 
 			else {
 				return ResponseEntity.status(HttpStatus.NOT_FOUND)
 						             .body("Hospital is not found with id"+id);
 			}	 
 		 
 	 }
      
	
	
	

}
