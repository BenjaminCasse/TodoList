package casse.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Bean de ma table utilisateur
 * @author benjamin cassé
 */
@Entity
@Table(name = "utilisateur")
public class Utilisateur {
	
	/**
	 * colonne id 
	 */
	@Id
	@GeneratedValue
	@Column(name="id_utilisateur")
	private int id;
	
	/**
	 * colonne nom
	 */
	@Column(name="nom")
	private String nom;
	
	/**
	 * colonne prenom
	 */
	@Column(name="prenom")
	private String prenom;
	
	/**
	 * colonne identifiant
	 */
	@Column(name="identifiant")
	private String identifiant;
	
	/**
	 * colonne mdp
	 */
	@Column(name="mdp")
	private String mdp;
	
	/**
	 * ma liste de taches
	 */
	@OneToMany(targetEntity = Tache.class, mappedBy = "utilisateur")
	private List<Tache> taches;
	
	public Utilisateur () {}

	@Override
	public String toString() {
		return nom + " " + prenom + " " + identifiant;
	}

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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
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
