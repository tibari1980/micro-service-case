package com.arcesi.gestioncase.dtos;

import java.io.Serializable;
import java.time.Instant;

import org.apache.commons.lang.StringUtils;

import com.arcesi.gestioncase.entities.Case;
import com.arcesi.gestioncase.requestDtos.CaseRequest;
import com.arcesi.gestioncase.responsedtos.CaseResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CaseDTO implements Serializable{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long caseId;
 	private String caseIdUnique;
 	private Instant createdAt;
 	private Instant updatedAt;
 	private String title;
 	private String description;
	
 	@Builder
 	public CaseDTO(Long caseId, String caseIdUnique, Instant createdAt, Instant updatedAt, String title,
			String description) {
		super();
		this.caseId = caseId;
		this.caseIdUnique = caseIdUnique;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.title = title;
		this.description = description;
	}
 	
 	
 	/**
	 * Méthode permettant de mapper un CaseDto vers Case
	 * 
	 * @param dto {@link CaseDTO }
	 * @return bean {@link Case}
	 */
	public static Case toEntity(CaseDTO dto) {
		if (dto == null) {
			//throw new InvalidEntityException(IConstanteMessageErrors.USER_NOT_VALIDE.getIdMessage());
			
		}
		return 	 Case.builder()
				.caseId(dto.getCaseId())
				.caseIdUnique(dto.getCaseIdUnique())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.title(dto.getTitle())
				.description(dto.getDescription())
				.build();

	}

	
	/**
	 * Méthode permettant de mapper un objet Case to CaseDto
	 * 
	 * @param bean {@link Case}
	 * @return dto {@link CaseDTO}
	 */
	public static CaseDTO fromBean(Case bean) {
		if (bean == null) {
			//throw new InvalidEntityException(IConstanteMessageErrors.USER_NOT_VALIDE.getIdMessage());
		}
		return CaseDTO.builder()
				.caseId(bean.getCaseId())
				.caseIdUnique(bean.getCaseIdUnique())
				.createdAt(bean.getCreatedAt())
				.updatedAt(bean.getUpdatedAt())
				.title(bean.getTitle())
				.description(bean.getDescription())
				.build();

	}
 	
	
	/**
	 * Méthode permettant de mapper un objet CaseRequest to CaseDto
	 * 
	 * @param request {@link CaseRequest}
	 * @return dto {@link CaseDTO}
	 */
	public static CaseDTO CaseRequestToCaseDTO(CaseRequest request) {
		if (request == null) {
			//throw new InvalidEntityException(IConstanteMessageErrors.USER_NOT_VALIDE.getIdMessage());
		}
		return CaseDTO.builder()
				.title(StringUtils.isNotEmpty(request.getTitle()) ? request.getTitle().toUpperCase() : null)
				.description(StringUtils.isNotEmpty(request.getDescription()) ? request.getDescription() : null)
				.build();

	}
	
	/**
	 * Méthode permettant de mapper un CaseDto vers CaseResponse
	 * 
	 * @param dto {@link CaseDTO }
	 * @return bean {@link CaseBean}
	 */
	public static CaseResponse CaseDTOToCaseResponse(CaseDTO dto) {
		if (dto == null) {
			//throw new InvalidEntityException(IConstanteMessageErrors.USER_NOT_VALIDE.getIdMessage());
		}
		return CaseResponse.builder()
				.caseIdUnique(dto.getCaseIdUnique())
				.title(dto.getTitle())
				.description(dto.getDescription())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build();

	}
}
