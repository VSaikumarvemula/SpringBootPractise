package com.Sathya.mvc.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor


public class ProductModel {
	 @NotBlank(message = "Product name is required")
	    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")    
	private String proName;
	 
	 @NotNull(message = "Product price is required")
	    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than zero")
	private double proPrice;
	 
	 
	 @NotBlank(message = "Brand is required")
	    @Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
	private String proBrand;
	 
	 @NotBlank(message = "Description is required")
	    @Size(max = 500, message = "Description must be less than 500 characters")
	private String proDescription;
	 
	 
	 @NotBlank(message = "Category is required")
	    @Pattern(regexp = "^[a-zA-Z]+$", message = "Category must only contain letters")
	private String proCategory;
	
	
	

	
}
