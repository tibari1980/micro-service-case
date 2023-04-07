package com.arcesi.gestioncase.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.gestioncase.entities.Case;
import com.arcesi.gestioncase.requestDtos.CaseRequest;
import com.arcesi.gestioncase.responsedtos.CaseResponse;

public interface CaseApi {

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CaseResponse> createCase(@RequestBody CaseRequest caseRequest);

	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CaseResponse> findOneById(@PathVariable(name = "id") Long id);

	
	//Exemple URL
	//http://localhost:8080/v1/cases/api/findByCodeUnique/8a4fa2f7-be5e-4960-a13f-4ea237d701f9
	@GetMapping(value = "/findByCodeUnique/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CaseResponse> findOneByCodeUniqueCase(@PathVariable(name = "code") String codeUnique);

	//Exemple URL
	//http://localhost:8080/v1/cases/api/all?page=0&limit=3
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CaseResponse>> findAllCase(@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@PutMapping(value = "/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CaseResponse> updateCase(@RequestBody  CaseRequest caseRequest, @PathVariable(name = "code") Long code);

	@DeleteMapping(value = "/{code}")
	public ResponseEntity<Object> deleteCase(@PathVariable(name = "code") Long code);

}
