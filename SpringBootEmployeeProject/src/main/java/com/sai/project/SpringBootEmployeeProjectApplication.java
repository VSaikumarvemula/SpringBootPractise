package com.sai.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootEmployeeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmployeeProjectApplication.class, args);
	}

}
