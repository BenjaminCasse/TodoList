package casse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import casse.model.Utilisateur;
import casse.service.UtilisateurService;



/**
 * @author benjamin cassé
 *
 */
@Controller
public class AccueilController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * méthode get 
	 * redirige vers notre vue login.html permettant de se connecter
	 */
	@GetMapping(path="/login")
	public String login() {
		return "login";
	}
	
	/**
	 * méthode get de logout qui redirige sur la page de login
	 * @param request
	 * @param response
	 * 
	 */
	@GetMapping(path="/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	/**
	 * méthode get redirigeant vers notre vue creationCompte.html pour y ajouter un utilisateur
	 * @param model
	 * 
	 */
	@GetMapping(path="/create-account")
	  public String creationCompte(Model model) {
	    model.addAttribute("utilisateur", new Utilisateur());
	    return "creationCompte";
	}
	
	/**
	 * méthode post permettant l'ajout de l'utilisateur en bd
	 * @param utilisateur
	 * @param model
	 * 
	 */
	@PostMapping(path="/create-account")
	  public String ajouterUtilisateur(@ModelAttribute Utilisateur utilisateur, Model model) {
		utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
		utilisateurService.saveUtilisateur(utilisateur);
	    model.addAttribute("utilisateur", utilisateur);
	    return "compteSucces";
	  }
}
