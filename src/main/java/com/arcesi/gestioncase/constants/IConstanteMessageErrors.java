package com.arcesi.gestioncase.constants;

public enum IConstanteMessageErrors {

	
	CASE_TOUS_LES_CHAMPS("Le Case n'est pas valide Veuillez vérifié la saisi de tous les champs"),
	CASE_TITLE_OBLIGATOIRE("Veuillez saisir le title "),
	CASE_TITLE_TAIL_NOT_VALID("Title doit être supérieur à 5 caractèrs et inférieur à 40 caractères"),
	CASE_DESCRIPTION_OBLIGATOIRE("Veuillez saisir la description"),
	CASE_DESCRIPTION_TAIL_NOT_VALID("Title doit être supérieur à 5 caractèrs et inférieur à 255 caractères"),
	CASE_INVALIDE("Case est invalide !!!!!"),
	CASE_ALREADY_EXIST("Case existe avec ce title")
	;

	public final String idMessage;

	private IConstanteMessageErrors(String idMessage) {
		this.idMessage = idMessage;
	}

	public String getIdMessage() {
		return idMessage;
	}

}
