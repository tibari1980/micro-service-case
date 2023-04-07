package com.arcesi.gestioncase.responsedtos;

import java.io.Serializable;
import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CaseResponse implements Serializable {

	private static final long serialVersionUID = 5792830148752845590L;
	private String caseIdUnique;
	private Instant createdAt;
	private Instant updatedAt;
	private String title;
	private String description;

	@Builder
	public CaseResponse(String caseIdUnique, Instant createdAt, Instant updatedAt, String title, String description) {
		super();
		this.caseIdUnique = caseIdUnique;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.title = title;
		this.description = description;
	}

}
