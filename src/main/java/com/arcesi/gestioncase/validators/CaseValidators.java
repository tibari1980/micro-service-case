package com.arcesi.gestioncase.validators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.arcesi.gestioncase.constants.IConstanteMessageErrors;
import com.arcesi.gestioncase.dtos.CaseDTO;

public class CaseValidators {

	/**
	 * Méthode permettant de validé un objet Caseif (dto == null) {
	 * errors.add(IConstanteMessageErrors.USER_NOT_VALIDE.getIdMessage()); }
	 * 
	 * @param case1 {@link CaseDTO }
	 * @return errors la liste des erreurs
	 */
	public static List<String> validate(CaseDTO dto) {
		List<String> errors = new ArrayList<String>();
		if (dto == null) {
			errors.add(IConstanteMessageErrors.CASE_TOUS_LES_CHAMPS.getIdMessage());
		}

		if (null != dto) {
			// Vérification title
			if (StringUtils.isEmpty(dto.getTitle())) {
				errors.add(IConstanteMessageErrors.CASE_TITLE_OBLIGATOIRE.getIdMessage());
			} else if (StringUtils.isNotBlank(dto.getTitle())
					&& (dto.getTitle().length() < 5 || dto.getTitle().length() > 50)) {
				errors.add(IConstanteMessageErrors.CASE_TITLE_TAIL_NOT_VALID.getIdMessage());
			}

			// Vérification de la description

			if (StringUtils.isEmpty(dto.getDescription())) {
				errors.add(IConstanteMessageErrors.CASE_DESCRIPTION_OBLIGATOIRE.getIdMessage());
			} else if (StringUtils.isNotBlank(dto.getTitle())
					&& (dto.getDescription().length() < 5 || dto.getDescription().length() > 255)) {
				errors.add(IConstanteMessageErrors.CASE_DESCRIPTION_TAIL_NOT_VALID.getIdMessage());
			}
		}
		return errors;
	}

}
