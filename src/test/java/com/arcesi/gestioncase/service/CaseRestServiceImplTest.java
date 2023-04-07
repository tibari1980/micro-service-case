package com.arcesi.gestioncase.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arcesi.gestioncase.dtos.CaseDTO;
import com.arcesi.gestioncase.entities.Case;
import com.arcesi.gestioncase.exceptions.EntityNotFoundException;
import com.arcesi.gestioncase.exceptions.ErrorsCodes;
import com.arcesi.gestioncase.exceptions.InvalidEntityException;
import com.arcesi.gestioncase.repositories.CaseRepository;
import com.arcesi.gestioncase.services.ICaseRestService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CaseRestServiceImplTest {

	@Autowired
	private ICaseRestService iCaseRestService;
	@Autowired
	private CaseRepository caseRepository;

	
 	@Test
	public void shouldSaveCaseWithSuccess() {
		// Création d'un Case
		Case expectedCase = Case.builder().caseIdUnique(UUID.randomUUID().toString()).createdAt(Instant.now())
				.updatedAt(Instant.now()).title("titleTest").description("DescriptionTest").build();
		// appeller le service
		CaseDTO savedCase = iCaseRestService.saveCase(CaseDTO.fromBean(expectedCase));

		Assertions.assertNotNull(savedCase);
		Assertions.assertNotNull(savedCase.getCaseId());
		Assertions.assertNotNull(savedCase.getCaseIdUnique());
		Assertions.assertEquals(savedCase.getTitle(), expectedCase.getTitle());
		Assertions.assertEquals(savedCase.getDescription(), expectedCase.getDescription());
	}
	
	
 	@Test
	public void shouldUpdateCaseWithSuccess() {
		// Creation d'un Case
		Case expectedCase = Case.builder()
				.createdAt(Instant.now())
				.updatedAt(Instant.now())
				.title("Case title  test")
				.description("Case description test").build();
		// applller le service
		CaseDTO caseSaved = iCaseRestService.saveCase(CaseDTO.fromBean(expectedCase));

		// Modification de la Case enregisté
		CaseDTO caseToUpdate = caseSaved;
		caseToUpdate.setTitle("Case title updated");
		
		// appellé le service de modification
		caseSaved = iCaseRestService.updateCase(caseSaved.getCaseId(), caseToUpdate);

		Assertions.assertNotNull(caseSaved);
		Assertions.assertNotNull(caseToUpdate.getCaseId());
		Assertions.assertEquals(caseToUpdate.getCaseIdUnique(), caseSaved.getCaseIdUnique());
		Assertions.assertEquals(caseToUpdate.getCaseId(), caseSaved.getCaseId());
		Assertions.assertEquals(caseToUpdate.getTitle(), caseSaved.getTitle());
		Assertions.assertEquals(caseToUpdate.getDescription(), caseSaved.getDescription());

	}	
	
	@Test
	public void itShouldCheckIfCaseExistTitle() {
		//Création d'un case
		Case expectedCase=Case.builder()
		.caseIdUnique(UUID.randomUUID().toString())
		.createdAt(Instant.now())
		.updatedAt(Instant.now())
		.title("Case title  test")
		.description("Case description test")
		.build();
		
		iCaseRestService.saveCase(CaseDTO.fromBean(expectedCase));
		
		//Vérification si un case avec le title existe.
		Case excpectedCase= caseRepository.findByTitleIgnoreCase("Case title  test");
		Assertions.assertNotNull(excpectedCase);
		
	}
	
	@Test
	public void shouldThrowInvalideCase() {
		//Préparation de l'object Case dans ce cas est vide
		Case expectedCase=Case.builder().build();
		//faire appelle au service
		InvalidEntityException expectedException= assertThrows(InvalidEntityException.class,()->iCaseRestService.saveCase(CaseDTO.fromBean(expectedCase)));
		
		//S'assurer que le code errors est égal au code récupéré dans l'exception ex 3001
		assertEquals(ErrorsCodes.CASE_NOT_VALID, expectedException.getErrorsCodes());
	
		//Le title et la description sont obligatoire 
		assertEquals(2, expectedException.getErrors().size());
		assertEquals("Veuillez saisir le title ", expectedException.getErrors().get(0));
		assertEquals("Veuillez saisir la description", expectedException.getErrors().get(1));
		
	}
	
	@Test
	public void shouldThrowsEntityNotFoundException() {
		
		EntityNotFoundException expectedException=assertThrows(EntityNotFoundException.class, ()->iCaseRestService.findCaseByCaseId(0L));
		assertEquals(ErrorsCodes.CASE_NOT_FOUND, expectedException.getErrorsCodes());
		assertEquals("Case avec  Id : 0 est introuvable dans notre base de données",expectedException.getMessage() );
		
	}
	
	@Test
	public void shouldThrowEntityNotFoundExceptionUniqueCode() {
		EntityNotFoundException expectedException=assertThrows(EntityNotFoundException.class, ()->iCaseRestService.findByCaseIdUnique("ET5487954"));
		assertEquals(ErrorsCodes.CASE_NOT_FOUND, expectedException.getErrorsCodes());
		assertEquals("Case avec  Code Unique  : ET5487954 est introuvable dans notre base de données",expectedException.getMessage() );
	}
	
	//Je dois implementer d'autre cas de test unitaire
	
}
