package com.arcesi.gestioncase.exceptions.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arcesi.gestioncase.exceptions.EntityNotFoundException;
import com.arcesi.gestioncase.exceptions.InvalidEntityException;


@RestControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorsDTO> handleException(EntityNotFoundException exception,WebRequest webRequest){
		final HttpStatus notFound=HttpStatus.NOT_FOUND;
		ErrorsDTO dto= ErrorsDTO.builder()
				 .code(exception.getErrorsCodes())
		          .httpCode(notFound.value())
		          .message(exception.getMessage())
		          .build();
		
		return new ResponseEntity<ErrorsDTO>(dto,notFound);
	} 
	
	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorsDTO> handleException(InvalidEntityException exception,WebRequest webRequest){
		final HttpStatus badRequest=HttpStatus.BAD_REQUEST;
		final ErrorsDTO dto=ErrorsDTO.builder()
				   .code(exception.getErrorsCodes())
				   .httpCode(badRequest.value())
				   .message(exception.getMessage())
				   .errors(exception.getErrors())
				   .build();
		return new ResponseEntity<ErrorsDTO>(dto,badRequest);
	}
}
