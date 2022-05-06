package casse.securite;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import casse.model.Utilisateur;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author benjamin cassé
 *
 */
public class MesDetailsUtilisateur implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;

    /**
     * @param utilisateur
     */
    public MesDetailsUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

	@Override
	public String toString() {
		return utilisateur.toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return utilisateur.getMdp();
	}

	@Override
	public String getUsername() {
		
		return utilisateur.getIdentifiant();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}