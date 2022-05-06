package casse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import casse.model.Utilisateur;
import casse.repository.UtilisateurRepository;
import casse.securite.MesDetailsUtilisateur;
 
/**
 * @author benjamin cassé
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UtilisateurRepository utilisateurRepository;
     
    /**
     * récupère l'utilisateur en bd à partir de son identifiant
     */
    @Override
    public UserDetails loadUserByUsername(String identifiant)
            throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.getUserByIdentifiant(identifiant);
         
        if (utilisateur == null) {
            throw new UsernameNotFoundException("L'utilisateur avec cet identifiant : " + identifiant + " n'existe pas.");
        }
         
        return new MesDetailsUtilisateur(utilisateur);
    }
 
}
