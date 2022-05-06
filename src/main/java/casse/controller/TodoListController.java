package casse.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import casse.model.Tache;
import casse.model.Utilisateur;
import casse.repository.UtilisateurRepository;
import casse.service.TacheService;
import casse.service.TagService;

/**
 * @author benjamin cassé
 *
 */
@Controller
public class TodoListController {
	
	@Autowired
    private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private TacheService tacheService;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * méthode get redirigeant vers notre vue todolist.html affichant la liste des tâches
	 * @param model
	 * 
	 */
	@GetMapping(path = "/todolist")
	public String afficherTaches(ModelMap model) {
		Utilisateur utilisateur = getUtilisateurConnecte();
		model.addAttribute("tags", tagService.getAllTag());
		model.put("taches", utilisateur.getTaches());
		model.addAttribute("utilisateur", utilisateur);
		return "todolist";
	}

	/**
	 * méthode récupérant l'instance de l'utilisateur connecté
	 * @return Utilisateur
	 * 
	 */
	private Utilisateur getUtilisateurConnecte() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String identifiant;
		
		if (principal instanceof UserDetails) {
			identifiant = ((UserDetails)principal).getUsername();
		} else {
			identifiant = principal.toString();
		}
		Utilisateur utilisateur = utilisateurRepository.getUserByIdentifiant(identifiant);
		return utilisateur;
	}
	
	/**
	 * méthode donnant la date courante
	 * @return Date
	 * 
	 */
	private Date dateCouranteFormat() {
		@SuppressWarnings("unused")
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

	/**
	 * méthode get redirigeant vers notre vue formAjoutTache.html permettant d'ajouter une tâche
	 * @param model
	 * 
	 */
	@GetMapping(path = "/ajouter-tache")
	public String formAjouterTache(Model model) {
		model.addAttribute("tags",tagService.getAllTag());
		model.addAttribute("tache", new Tache());
		return "formAjoutTache";
	}
	
	/**
	 * méthode post ajoutant en bd la tache saisie dans le formulaire et redirige ver la route /todolist
	 * @param model
	 * @param tache
	 * 
	 */
	@PostMapping(path = "/ajouter-tache")
	public String ajouterTache(Model model, Tache tache) {
		tache.setUtilisateur(getUtilisateurConnecte());
		tache.setEtat(false);
		
		tache.setDate(dateCouranteFormat());
		tacheService.saveTache(tache);
		return "redirect:/todolist";
	}

	/**
	 * méthode get redirigeant vers notre vue modifierTache.html permettant de modifier une tâche
	 * @param id
	 * @param model
	 * 
	 */
	@GetMapping(path = "/modifier-tache")
	public String formModifierTache(@RequestParam int id, Model model) {
		Tache tache = tacheService.getTacheById(id).get();
		model.addAttribute("tags", tagService.getAllTag());
		model.addAttribute("tache", tache);
		return "modifierTache";
	}
	
	/**
	 * méthode post modifiant en bd la tache saisie dans le formulaire et redirige ver la route /todolist
	 * @param tache
	 * 
	 */
	@PostMapping(value = "/modifier-tache")
	public String modifierTache(@ModelAttribute("Tache") Tache tache) {
		tache.setDate(dateCouranteFormat());
		tacheService.modifierTache(tache);
		return "redirect:/todolist";
	}
	
	/**
	 * méthode get permettant de supprimer une tâche et redirigeant vers la route /todolist
	 * @param id
	 * 
	 */
	@GetMapping(path = "/supprimer-tache")
	public String supprimerTache(@RequestParam int id) {
		tacheService.deleteTacheByID(id);
		return "redirect:/todolist";
	}
	
	/**
	 * méthode qui change l'état d'une tâche suite à un appel ajax et set la date de cloture si la tache est terminé
	 * @param id
	 * @param session
	 *
	 */
	@RequestMapping("/changeEtat")     
	@ResponseBody     
	public void changerEtat(@RequestParam("id") int id, HttpSession session ){       
		Tache tache = tacheService.getTacheById(id).get(); 
		Boolean etat = !tache.getEtat();
		tache.setEtat(etat);
		tache.setDate(dateCouranteFormat());
		System.out.println();
		if(etat) {
			tache.setDateCloture(dateCouranteFormat());
		}
		tacheService.saveTache(tache);
	}
}
