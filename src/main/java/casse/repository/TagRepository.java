package casse.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import casse.model.Tag;

/**
 * interface qui étends la JpaRepository afin d'ajouter nos propres méthodes pour manipuler notre table utilisateur
 * @author benjamin cassé
 * 
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
