package com.arcesi.gestioncase.exceptions;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author zeroual tibari ingénieur developpement
 */
@Getter
@Setter
public class InvalidEntityException extends RuntimeException {

	private static final long serialVersionUID = -8684485290233860892L;
	private ErrorsCodes errorsCodes;
	private List<String> errors;

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidEntityException(String message, Throwable cause, ErrorsCodes errorsCodes) {
		super(message, cause);
		this.errorsCodes = errorsCodes;
	}

	public InvalidEntityException(String message) {
		super(message);

	}

	public InvalidEntityException(String message, ErrorsCodes errorsCodes, List<String> errors) {
		super(message);
		this.errorsCodes = errorsCodes;
		this.errors = errors;
	}
	
	public InvalidEntityException(String message, ErrorsCodes errorsCodes) {
		super(message);
		this.errorsCodes = errorsCodes;
	}

}
