package com.sai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sai.project.entity.LoginEntity;
import com.sai.project.models.LoginModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller


public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@GetMapping("/RegisterUser")
	
    public String showForm(Model model) {
        
        model.addAttribute("user", new LoginModel());
        
        return "registerform";  
    }

	
	
	
	
	@PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") LoginModel user) {
		
        loginService.saveUser(user);
        return "succes"; // Redirect to a success page after saving
    }
	
	
	
	
	
	
	
	
	@GetMapping("/login")
	
	public String loginMethod(Model model) {
		return "login";
		
		
	}
	
	
	@PostMapping("/loginform")
	public String loginMethod(@RequestParam(value = "username") String username,
	                          @RequestParam(value = "password") String password,
	                          Model model, 
	                          RedirectAttributes redirectAttributes) { 

	    boolean isLoginSuccessful = loginService.details(username, password);
	    
	    if (isLoginSuccessful) {
	        redirectAttributes.addFlashAttribute("message", "Login successful! Welcome, " + username + "!");
	        return "main";
	    }
	    
	    
	    else {
	        String errorMessage = " Please try again. If  you dont have account please" +
	                              "<a href=\"/RegisterUser\">Register here</a>";
	        redirectAttributes.addFlashAttribute("error", errorMessage);
	        return "redirect:/login"; // Redirect back to the login page
	    }


	}

	
	
		
	
	
	
	

	@GetMapping("/home")
	public String index() {
	    return "main"; 
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
