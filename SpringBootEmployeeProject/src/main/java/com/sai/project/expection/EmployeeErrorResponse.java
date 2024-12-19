package com.sai.project.expection;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeErrorResponse {
		private LocalDateTime localDateTime;
		private String message; 
		private int status; 
	}


