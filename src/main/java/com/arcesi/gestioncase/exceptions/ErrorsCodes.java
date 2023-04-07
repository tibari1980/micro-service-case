package com.arcesi.gestioncase.exceptions;
/**
 * 
 * @author Zeroual tibari 
 * Ingénieur developpement
 *
 */
//enum contient les enums pour traiter les errors
public enum ErrorsCodes {


	CASE_NOT_FOUND(3000),
	CASE_NOT_VALID(3001),
	CASE_TITLE_NOT_VALID(3002),
	CASE_DESCRIPTION_NOT_VALID(3003);
	
	private int code;
	
	private ErrorsCodes(int code) {
		this.code=code;
	}

	public int getCode() {
		return code;
	}

}
