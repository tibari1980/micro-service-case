package com.arcesi.gestioncase.services;

import java.util.List;

import com.arcesi.gestioncase.dtos.CaseDTO;

/**
 * 
 * @author tibari Zeroual Ingénieur développement
 *
 */
public interface ICaseRestService {

	/**
	 * Méthode permettant la création d'un Case .
	 * 
	 * @param dto {@link CaseDTO }
	 * @return dto {@link CaseDTO }
	 */
	public CaseDTO saveCase(final CaseDTO dto);

	/**
	 * Méthode permettant la récupération d'un case avec l'identifiant.
	 * 
	 * @param caseId {@link Long }
	 * @return dto {@link CaseDTO }
	 */
	public CaseDTO findCaseByCaseId(final Long caseId);

	/**
	 * Méthode permettant la récupération d'un case avec l'identifiant unique.
	 * 
	 * @param codeUnique {@link String }
	 * @return dto {@link CaseDTO }
	 */
	public CaseDTO findByCaseIdUnique(final String codeUnique);

	/**
	 * Méthode permettant de modifié un case avec l'identifiant
	 * @param id {@link Long}
	 * @param dto {@link CaseDTO }
	 * @return dto {@link  CaseDTO }
	 */
	public CaseDTO updateCase(final Long id, final CaseDTO dto);

	/**
	 *Méthode permettant de supprimé un Case avec l'identifiant 
	 * @param id {@link  Long}
	 */
	public void deleteCase(final Long id);

	/**
	 * 
	 * @param page {@link Integer }
	 * @param limit {@link Integer }
	 * @return dtos {@link List<CaseDTO> }
	 */
	public List<CaseDTO> findAllCases(final int page, final int limit);
}
