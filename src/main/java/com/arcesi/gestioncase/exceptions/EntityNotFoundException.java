package com.arcesi.gestioncase.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {



	private static final long serialVersionUID = 3373293083265026734L;
	private ErrorsCodes errorsCodes;

	public EntityNotFoundException(String message,Throwable cause) {
		super(message,cause);
		
	}
	
	public EntityNotFoundException(String message,Throwable cause,ErrorsCodes errorsCodes) {
		super(message,cause);
		this.errorsCodes=errorsCodes;
		
	}
	
	public EntityNotFoundException(String message,ErrorsCodes errorsCodes) {
		super(message);
		this.errorsCodes=errorsCodes;
		
	}
	
	

}
