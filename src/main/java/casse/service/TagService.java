package casse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import casse.model.Tag;
import casse.repository.TagRepository;

/**
 * mes services de la table Tag
 * @author benjamin cassé
 */
@Service
public class TagService {

	@Autowired
	private TagRepository repo;
	
	/**
	 * sauvegarde un tag en bd
	 * @param tag
	 * 
	 */
	public Tag saveTag(Tag tag) {
		return repo.save(tag);
	}
	
	/**
	 * @return une liste de tag
	 */
	public List<Tag> getAllTag() {
		return repo.findAll();
	}

	/**
	 * @param id
	 * @return un container de l'objet tag
	 */
	public Optional<Tag> getTagById(int id) {
		return repo.findById(id);
	}
	
	/**
	 * sauvegarde un utilisateur en bd
	 * @param tag
	 * 
	 */
	public void modifierTag(Tag tag) {
		repo.save(tag);
	}

	/**
	 * supprimer un tag par id
	 * @param id
	 * 
	 */
	public void deleteTagByID(int id) {
		Optional<Tag> tag = repo.findById(id);
		repo.delete(tag.get());
	}
}
