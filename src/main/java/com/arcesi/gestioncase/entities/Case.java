package com.arcesi.gestioncase.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "CASES")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Case implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CASE_ID", nullable = false, updatable = false, unique = true)
	private Long caseId;
	@Column(name = "CASE_ID_UNIQUE", nullable = false, updatable = false, unique = true)
	private String caseIdUnique;
	@CreatedDate
	@Column(name = "CREEATED_AT", nullable = false, updatable = false, insertable = true)
	private Instant createdAt;
	@LastModifiedDate
	@Column(name = "UPDATED_AT", nullable = true, updatable = true, insertable = true)
	private Instant updatedAt;
	@Column(name = "TITLE", insertable = true,unique = true,updatable = true,length = 50)
	private String title;
	@Column(name = "DESCRIPTION",insertable = true,updatable = true,length = 255)
	private String description;

}
