package com.Sathya.mvc.controller;

import java.time.LocalDate; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Sathya.mvc.entities.ProductEntity;
import com.Sathya.mvc.models.ProductModel;




@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProductData(ProductModel productModel) {
        double price = productModel.getProPrice();
        String category = productModel.getProCategory();
        double dprice = 0.0;

        switch (category) {
            case "Mobile":
                dprice = price * 0.1;
                break;
            case "Laptop":
                dprice = price * 0.15;
                break;
            case "Printer":
                dprice = price * 0.2;
                break;
            case "Accessories":
                dprice = price * 0.25;
                break;
        }
       
        // Read data from model and set it to entity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProName(productModel.getProName());
        productEntity.setProPrice(price);
        productEntity.setProCategory(category);
        productEntity.setProDescription(productModel.getProDescription());
        productEntity.setProBrand(productModel.getProBrand());
        productEntity.setDprice(dprice);
        productEntity.setCreatedAt(LocalDate.now());
        productEntity.setCreatedBy(System.getProperty("user.name"));
       // save the entity class object 
        productRepository.save(productEntity);
    }

    
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }
    
    
    
  // delete operation 
    public void deleteProductById(long id) {
        productRepository.deleteById(id);  // Delete the product from the database
    }

   
    
    
    
    // edit 
    public Optional<ProductEntity> findProductById(Long id) {
        return productRepository.findById(id);
    }

  	
    

   
    
    
    
 
 // Method to update product data
    public void updateProductData(Long id, ProductModel productModel) {
        Optional<ProductEntity> existingProductOpt = productRepository.findById(id);
        
        if (existingProductOpt.isPresent()) {
            ProductEntity existingProduct = existingProductOpt.get();
            
            // Update fields
            existingProduct.setProName(productModel.getProName());
            existingProduct.setProBrand(productModel.getProBrand());
            existingProduct.setProPrice(productModel.getProPrice());
            existingProduct.setProDescription(productModel.getProDescription());
            existingProduct.setProCategory(productModel.getProCategory());
            existingProduct.setDprice(calculateDiscountedPrice(productModel.getProPrice(), productModel.getProCategory()));
            existingProduct.setCreatedAt(LocalDate.now()); // You might want to keep the original creation date instead
            existingProduct.setCreatedBy(System.getProperty("user.name"));
            
            // Save the updated entity
            productRepository.save(existingProduct);
        }
        
    }
    

    // Calculate discounted price based on category
    private double calculateDiscountedPrice(double price, String category) {
        double dprice = 0.0;
        switch (category) {
            case "Mobile":
                dprice = price * 0.1;
                break;
            case "Laptop":
                dprice = price * 0.15;
                break;
            case "Printer":
                dprice = price * 0.2;
                break;
            case "Camera":
                dprice = price * 0.25;
                break;
        }
        return dprice;
    }
            
  
    
    
    
    // Fetch product by ID
    public Optional<ProductEntity> findProductById1(Long id) {
        return productRepository.findById(id);
    }
   

    
}

	    
	    
	  		