package com.sai.project.expection;

import java.time.LocalDateTime;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> exceptionHandling1(EmployeeNotFoundException exception)
	{	EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();
		employeeErrorResponse.setLocalDateTime(LocalDateTime.now());
		employeeErrorResponse.setMessage(exception.getMessage());
		employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(employeeErrorResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> exceptionHandling2(MethodArgumentNotValidException exception)
	{
		Map<String, String> errorMap = new LinkedHashMap<>();
		
		exception.getBindingResult()
				 .getFieldErrors()
				 .forEach(error ->
				 {
					String key = error.getField();
					String value = error.getDefaultMessage();
					
					errorMap.put(key, value);
				 });
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(errorMap);
	}
	
	
	

}
