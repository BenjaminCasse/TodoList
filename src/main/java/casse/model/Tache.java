package casse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * Bean de ma table taches
 * @author benjamin cassé
 */
@Entity
@Table(name = "tache")
public class Tache {
	
	/**
	 * colonne id
	 */
	@Id
	@GeneratedValue
	@Column(name="id_tache")
	private int id;
	
	/**
	 * colonne titre
	 */
	@Column(name="titre")
	private String titre;
	/**
	 * colonne description
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * colonne etats
	 */
	@Column(name="etat")
	private Boolean etat;
	
	/**
	 * colonne date
	 */
	@Column(name="date_")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	/**
	 * colonne dateCloture
	 */
	@Column(name="dateCloture")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCloture;

	/**
	 * clef étrangère de la table utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
	
	@ManyToMany()
    @JoinTable(
        name = "Posseder", 
        joinColumns = { @JoinColumn(name = "id_tache") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_tag") }
    )
    List<Tag> tags = new ArrayList<>();
	
	public Tache () { /* ignore */ }

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
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the etat
	 */
	public Boolean getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the dateCloture
	 */
	public Date getDateCloture() {
		return dateCloture;
	}

	/**
	 * @param dateCloture the dateCloture to set
	 */
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}
}
