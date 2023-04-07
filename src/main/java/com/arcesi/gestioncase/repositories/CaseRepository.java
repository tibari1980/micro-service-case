package com.arcesi.gestioncase.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.gestioncase.entities.Case;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

	Optional<Case> findByCaseIdUnique(final String codeUnique);

	Case findByTitleIgnoreCase(String title);

}
