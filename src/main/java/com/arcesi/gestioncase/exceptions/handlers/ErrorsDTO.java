package com.arcesi.gestioncase.exceptions.handlers;

import java.util.List;

import com.arcesi.gestioncase.exceptions.ErrorsCodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorsDTO {

	private Integer httpCode;
	private ErrorsCodes code;
	private String message;
	private List<String> errors;

}
