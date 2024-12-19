package com.Sathya.mvc.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sathya.mvc.entities.ProductEntity;
import com.Sathya.mvc.models.ProductModel;

import jakarta.validation.Valid;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    
    // display the form and submit form in database                 .......task-1
    // Display form for adding a product   
    @GetMapping("/productform")
    // sending the empty object ....  we are sending the form with empty object 
    public String getProductForm(Model model) {
        ProductModel productModel = new ProductModel();
        model.addAttribute("productModel", productModel);
        // return "add-product";
        model.addAttribute("page", "productform");
    	return "index";
    }

    
    
    // Handle form submission to save a new product
//    @PostMapping("/submitProduct")
//    public String saveProduct(@ModelAttribute("productModel") ProductModel productModel,Model model) {
//        productService.saveProductData(productModel);
//       // return "success";            // View for success after saving the product
//        model.addAttribute("page", "resubmit");
//    	return "index";
//    }

    
 // Handle form submission to save a new product
    @PostMapping("/submitProduct")
    public String saveProduct(@Valid @ModelAttribute("productModel") ProductModel productModel, 
                             BindingResult bindingResult, Model model) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the product form with errors
            model.addAttribute("page", "productform");
            
            return "index";  // return to the form page (assuming "productForm.html" is your Thymeleaf template)
        }

      

        // Redirect to a success page or product list page after successful validation
        model.addAttribute("page", "resubmit");
        productService.saveProductData(productModel);
        return "index";  // assuming there is a page that lists products
    }
    
    
    
    
    

    // Display the list of all products                                            ...task-2
    @GetMapping("/getproducts")
    public String findAllProducts(Model model) {
        List<ProductEntity> products = productService.findAllProducts();
        model.addAttribute("products", products);
        //return "product-list";  // View for displaying the product list
        model.addAttribute("page", "getproducts");
    	return "index";
    }
    
    
    
    
    
    
    
    
  // Delete a product by ID........task-3
    
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long proId) {
        productService.deleteProductById(proId);
        // Redirect to the product list after deletion
        return "redirect:/getproducts";
    }


    
  
    

 // edit and update............task-4
    @GetMapping("/edit")                                                                   
    public String editProductForm(@RequestParam("id") Long id, Model model) {                         
        Optional<ProductEntity> product = productService.findProductById(id);                   
                                                                                                
        if (product.isPresent()) {                                                              
            model.addAttribute("productModel", product.get());                                  
           // return "edit-product"; // View for editing product    
            model.addAttribute("page", "editform");
        	return "index";
        } else {                                                                                
            model.addAttribute("errorMessage", "Product not found");                            
            return "error"; // Redirect to an error page if not found    
            
        }                                                                                       
    }
    
	
 // Update the product by ID	
    @PostMapping("/update")
    public String updateProduct(@RequestParam("id") Long proId, @ModelAttribute ProductModel productModel) {
        productService.updateProductData(proId, productModel);
        return "redirect:/getproducts"; // Redirect to the product list after updating
    }

    
    
   
    
    
    
 // filter by data using id.................task-5
 // Handling POST request for fetching product by ID
    @GetMapping("/postid")
    public String getWeb1(Model model) {
       // return "Id"; // Returns the id.html template
    	 model.addAttribute("page", "getproduct");
     	return "index";
    	
    }

    
    
    // Handling POST request for fetching product by ID
    @PostMapping("/get-product-by-id")
    public String getProductById(@RequestParam("id") Long id, Model model) {
        Optional<ProductEntity> product = productService.findProductById1(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            model.addAttribute("error", "Product not found");
        }
       // return "single"; // Returns the single.html template for product details
        
        model.addAttribute("page", "productdetails");
     	return "index";
    }


    
    
  
    // contact and about pages returning .......here...........
    @GetMapping("/about")
    public String getabout(Model model) {
    	//return "about";
    	model.addAttribute("page", "about");
    	return "index";
    }
    
    
    @GetMapping("/contact")
    public String getcontact(Model model) {
    	//return "contact";
    	model.addAttribute("page", "contact");
    	return "index";
    }

    // index.html
    @GetMapping("/")
    public String getindex() {
    	return "index";
    }
    
    
    
}