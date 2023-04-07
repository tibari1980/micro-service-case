package com.arcesi.gestioncase.service.imp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.gestioncase.constants.IConstanteMessageErrors;
import com.arcesi.gestioncase.dtos.CaseDTO;
import com.arcesi.gestioncase.entities.Case;
import com.arcesi.gestioncase.exceptions.EntityNotFoundException;
import com.arcesi.gestioncase.exceptions.ErrorsCodes;
import com.arcesi.gestioncase.exceptions.InvalidEntityException;
import com.arcesi.gestioncase.repositories.CaseRepository;
import com.arcesi.gestioncase.services.ICaseRestService;
import com.arcesi.gestioncase.validators.CaseValidators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CaseRestServiceImpl implements ICaseRestService {

	private CaseRepository caseRepository;

	@Override
	public CaseDTO saveCase(CaseDTO case1) {
		log.info("Inside methode saveCase in Service CaseRestServiceImpl :", case1);
		// Traitement métier pour la validation de l'objet case1
		List<String> errors = CaseValidators.validate(case1);

		if (!errors.isEmpty()) {
			log.error("Case is not valid");
			throw new InvalidEntityException(IConstanteMessageErrors.CASE_INVALIDE.getIdMessage(),
					ErrorsCodes.CASE_NOT_VALID, errors);
		}
		//Vérifier si le Titre Exist
		Case checkIfCaseExist = caseRepository.findByTitleIgnoreCase(case1.getTitle());
		if(null!=checkIfCaseExist) {
			log.info("Case existe dans notre base de données : title {}  !!",case1.getTitle());
			throw new InvalidEntityException(IConstanteMessageErrors.CASE_ALREADY_EXIST.getIdMessage());
		} 
		case1.setCreatedAt(Instant.now());
		case1.setUpdatedAt(null);
		case1.setCaseIdUnique(UUID.randomUUID().toString());
		Case caseCreated = caseRepository.save(CaseDTO.toEntity(case1));
		log.info("Case : {}  created successfully !!", caseCreated);
		return CaseDTO.fromBean(caseCreated);
	}

	@Override
	public CaseDTO findCaseByCaseId(Long caseId) {
		log.info("Inside methode findCaseByCaseId in Service CaseRestServiceImpl :", caseId);
		Case caseBean;
		try {
			caseBean = caseRepository.findById(caseId).get();
		} catch (Exception e) {
			log.error("Case not found in our data base : {}", caseId);
			throw new EntityNotFoundException(
					"Case avec  Id : " + caseId + " est introuvable dans notre base de données",
					ErrorsCodes.CASE_NOT_FOUND);
		}
		log.info("Case : {}  finded successfully with id !!", caseBean);
		return CaseDTO.fromBean(caseBean);
	}

	@Override
	public CaseDTO findByCaseIdUnique(String codeUnique) {
		log.info("Inside methode findByCaseIdUnique in Service CaseRestServiceImpl :", codeUnique);
		Case caseBean;
		try {
			caseBean = caseRepository.findByCaseIdUnique(codeUnique).get();
		} catch (Exception e) {
			log.error("Case not found in our db : {}", codeUnique);
			throw new EntityNotFoundException(
					"Case avec  Code Unique  : " + codeUnique + " est introuvable dans notre base de données",
					ErrorsCodes.CASE_NOT_FOUND);

		}
		log.info("Case : {}  finded successfully with codeUnique !!", caseBean);
		return CaseDTO.fromBean(caseBean);
	}

	@Override
	public CaseDTO updateCase(Long id, CaseDTO case1) {
		log.info("Inside method updateCase in service CaseRestServiceImp : {}", case1);

		// Traitement métier pour la validation de l'objet case1
		List<String> errors = CaseValidators.validate(case1);

		if (!errors.isEmpty()) {
			log.error("Case is not valid");
			throw new InvalidEntityException(IConstanteMessageErrors.CASE_INVALIDE.getIdMessage(),
					ErrorsCodes.CASE_NOT_VALID, errors);
		}

		Case caseInOurDB;
		try {
			caseInOurDB = caseRepository.findById(id).get();
		} catch (Exception e) {
			log.error("Case Not found in our data base {}", id);
			throw new EntityNotFoundException("Case avec le l'identifiant  : " + id + " est introuvable dans la bdd",
					ErrorsCodes.CASE_NOT_FOUND);
		}

		case1.setCaseId(caseInOurDB.getCaseId());
		case1.setCreatedAt(caseInOurDB.getCreatedAt());
		case1.setUpdatedAt(Instant.now());
		case1.setCaseIdUnique(caseInOurDB.getCaseIdUnique());
		Case caseUpdated = caseRepository.saveAndFlush(CaseDTO.toEntity(case1));
		log.info("Case : {}  updated successfully  !!", caseUpdated);
		return CaseDTO.fromBean(caseUpdated);
	}

	@Override
	public void deleteCase(Long id) {
		log.info("Inside method deleteCase in CaseRestServiceImp : {}", id);
		Case bean;
		try {
			bean = caseRepository.findById(id).get();
		} catch (Exception e) {
			log.error("Case Not found in our data base {}", id);
			throw new EntityNotFoundException(
					"Case avec le code  : " + id + " est introuvable dans la base de données.",
					ErrorsCodes.CASE_NOT_FOUND);
		}

		caseRepository.delete(bean);
		log.info("Case with id : {} deleted successufully !!", id);

	}

	@Override
	public List<CaseDTO> findAllCases(int page, int limit) {
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("caseId").descending());
		Page<Case> pageCases = caseRepository.findAll(pageable);
		List<Case> listeCasesBeans = pageCases.getContent();
		if (listeCasesBeans.isEmpty()) {
			log.error("List of clients is empty :{}", listeCasesBeans);
			throw new EntityNotFoundException("aucun cases trouvés dans notre base de données",
					ErrorsCodes.CASE_NOT_FOUND);
		}

		List<CaseDTO> lstCaseDTOs = listeCasesBeans.stream().map(CaseDTO::fromBean).collect(Collectors.toList());
		return lstCaseDTOs;
	}

}
