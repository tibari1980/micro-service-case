package com.arcesi.gestioncase.requestDtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CaseRequest implements Serializable {

	 
	private static final long serialVersionUID = 1L;
	
 	private String title;
 	private String description;
	
 	@Builder
 	public CaseRequest(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
 	
 	
}
