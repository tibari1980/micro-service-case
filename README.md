# Techno SPRING BOOT BY TIBARI ZEROUAL
# Mini-Projet-Java-Springboot
# Objectif Application
 L'objectifi de  ce mini projet  est de créer une application Resful Web Service à l'aide de Spring boot.
Ce mini projet ayant  les les fonctionnalités CRUD :CREATE ,READ, UPDATE ,DELETE
#But Gerer Les CASES 
# STEP :
#=============================Model ou Entities ============================================
Création de l'entité CASE.(avec les annotations jpa)
(utilisation de lombook) 
#============================= repository ==================================================
Creation de l'interface Repository ( CaseRepository) (méthode crud pour gerer l'entité
#============================= service======================================================
Creation d'un interface (contenant les méthode  metier ) IRestCaseService)
Creation d'une implémentation de l'interface contenant les services (IRestCaseService)
#============================ WEB ==========================================================
Creation d'une interface contenant les méthodes controllers 
exemple :
-getAllCase
-findByCaseId
-findByCodeUnique
-saveCase
-updateCase
-deleteCase
Creation d'une classe qui implemente cette classe ApiRestController

==================================================================

#=================================== Ajoute la documentation ========================
# Dépendence 
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.14</version>
		</dependency>
#===================================================================================

http://localhost:8080/swagger-ui/index.html


#===================================  Imporet les mothode pour tester insomia  postman restAdvancedClient==
http://localhost:8080/v3/api-docs

#============================================== Realisation des test Unitaire ============================

# ajouté la dépendance 

                 <dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<scope>test</scope>
		</dependency>
#=============================================================
Fin Test Api
