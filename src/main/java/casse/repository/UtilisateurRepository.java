package casse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import casse.model.Utilisateur;

/**
 * interface qui étends la JpaRepository afin d'ajouter nos propres méthodes pour manipuler notre table utilisateur
 * @author benjamin cassé
 * 
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	public Utilisateur getUserByIdentifiant(@Param("identifiant") String identifiant);
}
