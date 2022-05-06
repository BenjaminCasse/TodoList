package casse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import casse.model.Tache;
import casse.repository.TacheRepository;

/**
 * mes services de la table tache
 * @author benjamin cassé
 * 
 */
@Service
public class TacheService {

	@Autowired
	private TacheRepository repo;
	
	/**
	 * Sauvegarde une tache en bd
	 * @param tache
	 * @return instance de tache sauvegardé en bd
	 */
	public Tache saveTache(Tache tache) {
		return repo.save(tache);
	}

	/**
	 * @param identifiant
	 * @return la liste des tâches d'un utilisateur
	 */
	public List<Tache> getTachesByUtilisateur(String identifiant) {
		return repo.findByUtilisateur(identifiant);
	}

	/**
	 * @param id
	 * @return un container de l'objet tache
	 */
	public Optional<Tache> getTacheById(int id) {
		return repo.findById(id);
	}
	
	/**
	 * sauvegarde une tache en bd
	 * @param tache
	 * 
	 */
	public void modifierTache(Tache tache) {
		repo.save(tache);
	}

	/**
	 * supprimer une tache par son id
	 * @param id
	 * 
	 */
	public void deleteTacheByID(int id) {
		Optional<Tache> tache = repo.findById(id);
		repo.delete(tache.get());
	}
}
