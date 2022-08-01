package com.estockmarket.query.domain.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidCompanyCodeException.class)
	public ResponseEntity<Object> inputExceptions(InvalidCompanyCodeException exception, WebRequest webRequest) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage("Company ID doesn't exist");
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		return entity;
	}
	
	@ExceptionHandler(NoStocksExistsException.class)
	public ResponseEntity<Object> inputExceptions(NoStocksExistsException exception, WebRequest webRequest) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage("No stocks exists for this company code");
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		return entity;
	}

}