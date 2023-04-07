package com.arcesi.gestioncase;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arcesi.gestioncase.entities.Case;
import com.arcesi.gestioncase.repositories.CaseRepository;

@SpringBootApplication
public class MicroServiceCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceCaseApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner run(final CaseRepository caseRepository) {
		return args->{
			for(int i=1;i<10;i++) {
				Case case1=Case
						.builder()
						.caseIdUnique(UUID.randomUUID().toString())
						.createdAt(Instant.now())
						.updatedAt(Instant.now())
						.title("title"+i)
						.description("Descritption"+i)
						.build();	
				caseRepository.save(case1);
			}
			//affiche la liste des cases
			List<Case> cases=caseRepository.findAll();
			cases.forEach(c->{
				System.out.println(c.toString());
			});
		};
	}
}
