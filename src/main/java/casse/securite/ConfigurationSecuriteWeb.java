package casse.securite;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import casse.service.UserDetailsServiceImpl;
 
/**
 * @author benjamin cassé
 *
 */
@Configuration
@EnableWebSecurity
public class ConfigurationSecuriteWeb extends WebSecurityConfigurerAdapter {
 
    /**
     * @return une instance qui loads les données spécifiques d'un utilisateur
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    /**
     * @return une instance BCryptPasswordEncoder implémentant PasswordEncoder et qui utilise BCrypt
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    /**
     * @return une instance DaoAuthenticationProvider qui implémente AuthenticationProvider afin de récupèrer les détails de l'utilisateur à partir d'un UserDetailsService
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    /**
     *méthode de configuration permettant de définir notre moyen d'authentification
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    /**
     * configuration sécurité 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/img/todolist.jpg").permitAll()
        	.antMatchers("/create-account").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/todolist", true)
			.permitAll()
            .and()
            .logout().permitAll()
        	.and()
        	.csrf().disable().cors();
    }
}