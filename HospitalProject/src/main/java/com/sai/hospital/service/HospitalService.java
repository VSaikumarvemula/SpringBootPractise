package com.sai.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.hospital.entity.Hospital;
import com.sai.hospital.repository.HospitalRepository;

@Service
public class HospitalService {
	@Autowired
	HospitalRepository  hospitalRepository;

	
	
	public Hospital saveHospital(Hospital hospital) {
	
		return hospitalRepository.save(hospital);
	}



	public List<Hospital> saveHospitals(List<Hospital> hospitals) {
	 
		            return  hospitalRepository.saveAll(hospitals);	            	            
	}


	

	public List<Hospital> getHospitals() {
		
		   return  hospitalRepository.findAll();
	}



	public Optional<Hospital> getHospitalById(Long id) {
		
		       return hospitalRepository.findById(id);
	}
	

	
	
	
	
	
}
