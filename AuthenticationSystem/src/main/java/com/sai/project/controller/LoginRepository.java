package com.sai.project.controller;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.sai.project.entity.LoginEntity;

@Repository

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
	
	
	
	
	LoginEntity findByUsername(String username);
	
	

}
