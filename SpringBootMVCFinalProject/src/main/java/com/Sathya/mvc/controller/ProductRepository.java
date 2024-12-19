package com.Sathya.mvc.controller;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.Sathya.mvc.entities.ProductEntity;

@Repository
	
	public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

	}


