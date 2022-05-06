package casse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import casse.model.Utilisateur;
import casse.repository.UtilisateurRepository;


/**
 * mes services de la table utilisateur
 * @author benja
 * 
 */
/**
 * @author benjamin cassé
 *
 */
@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository repo;
	
	/**
	 * sauvegarde un utilisateur en bd
	 * @param utilisateur
	 * @return instance de l'utilisateur sauvegardé en bd
	 */
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		return repo.save(utilisateur);
	}
}
