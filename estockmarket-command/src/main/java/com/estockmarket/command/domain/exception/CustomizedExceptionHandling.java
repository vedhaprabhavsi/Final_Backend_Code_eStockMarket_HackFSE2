package com.estockmarket.command.domain.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedExceptionHandling.class);
	
	@ExceptionHandler(CompanyAlreadyExistsException.class)
	public ResponseEntity<Object> inputExceptions(CompanyAlreadyExistsException exception, WebRequest webRequest) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage("Company Code Already Exists");
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		LOGGER.debug("Company Code Already Exists");
		return entity;
	}
}