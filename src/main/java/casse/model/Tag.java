package casse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Bean de ma table taches
 * @author benjamin cassé
 */
@Entity
@Table(name = "tag")
public class Tag {
	/**
	 * colonne id
	 */
	@Id
	@GeneratedValue
	@Column(name="id_tag")
	private int id;
	
	/**
	 * colonne nom
	 */
	@Column(name="nom")
	private String nom;
	
	@ManyToMany(mappedBy = "tags", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Tache> taches = new ArrayList<>();
	
	public Tag () {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the taches
	 */
	public List<Tache> getTaches() {
		return taches;
	}

	/**
	 * @param taches the taches to set
	 */
	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	
	
	
}
