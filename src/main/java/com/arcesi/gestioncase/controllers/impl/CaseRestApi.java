package com.arcesi.gestioncase.controllers.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.gestioncase.controllers.CaseApi;
import com.arcesi.gestioncase.dtos.CaseDTO;
import com.arcesi.gestioncase.entities.Case;
import com.arcesi.gestioncase.requestDtos.CaseRequest;
import com.arcesi.gestioncase.responsedtos.CaseResponse;
import com.arcesi.gestioncase.services.ICaseRestService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1/cases")
public class CaseRestApi implements CaseApi {

	private ICaseRestService iCaseRestService;

	@Override
	public ResponseEntity<CaseResponse> createCase(CaseRequest caseRequest) {
		log.info("Inside method createCase in controller CaseRestApi : case info : {} ", caseRequest);
		CaseDTO caseDTO = CaseDTO.CaseRequestToCaseDTO(caseRequest);
		CaseDTO caseSaved = iCaseRestService.saveCase(caseDTO);
		return new ResponseEntity<CaseResponse>(CaseDTO.CaseDTOToCaseResponse(caseSaved), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CaseResponse> findOneById(Long id) {
		log.info("Inside methode findOneById in controller CaseRestApi  caseId: {}", id);
		if (null == id) {
			log.error("Case Id is not valid try again: {} ", id);
			return null;
		}
		CaseDTO caseDtoFind = iCaseRestService.findCaseByCaseId(id);
		return new ResponseEntity<CaseResponse>(CaseDTO.CaseDTOToCaseResponse(caseDtoFind), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseResponse> findOneByCodeUniqueCase(String codeUnique) {
		log.info("Inside methode findOneByCodeUniqueCase in controller CaseRestApi  caseId: {}", codeUnique);
		if (null == codeUnique) {
			log.error("Case codeUnique is not valid try again: {} ", codeUnique);
			return null;
		}

		CaseDTO caseDTOFind = iCaseRestService.findByCaseIdUnique(codeUnique);
		return new ResponseEntity<CaseResponse>(CaseDTO.CaseDTOToCaseResponse(caseDTOFind), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CaseResponse>> findAllCase(int page, int limit) {
		log.info("Inside method findallCase in controllers CaseRestApi : page :{} size : {}", page, limit);
		//List<Case> cases = iCaseRestService.findAllCases(page, limit);
		List<CaseDTO> lstCaseDtos=iCaseRestService.findAllCases(page, limit);
		List<CaseResponse> lstCaseResponses=lstCaseDtos.stream().map(CaseDTO::CaseDTOToCaseResponse).collect(Collectors.toList());
		return new ResponseEntity<List<CaseResponse>>(lstCaseResponses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseResponse> updateCase(CaseRequest case1, Long code) {
		log.info("Inside method updateCase in controllers CaseRestApi : case {} code : {}", case1, code);
		if (code == null) {
			log.info("code Case is null", code);
			throw new RuntimeException("code ne peut pas être null");
		}
		CaseDTO caseUpdatedDTO=iCaseRestService.updateCase(code, CaseDTO.CaseRequestToCaseDTO(case1));
		return new ResponseEntity<CaseResponse>( CaseDTO.CaseDTOToCaseResponse(caseUpdatedDTO), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Object> deleteCase(Long code) {
		log.info("Inside method deleteCase in controllers CaseRestApi : {}", code);
		if (null == code) {
			log.error("code is not valid try again :{}", code);
			throw new RuntimeException("code is not valid  Empty");
		}
		iCaseRestService.deleteCase(code);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
