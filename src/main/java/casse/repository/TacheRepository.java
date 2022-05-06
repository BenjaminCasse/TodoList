package casse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import casse.model.Tache;

/**
 * interface qui étends la JpaRepository afin d'ajouter nos propres méthodes pour manipuler notre table tache
 * @author benjamin cassé
 * 
 */
public interface TacheRepository extends JpaRepository<Tache, Integer> {
	public List<Tache> findByUtilisateur(String identifiant);
}
