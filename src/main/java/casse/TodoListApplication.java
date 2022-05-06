package casse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import casse.repository.UtilisateurRepository;

/**
 * @author benjamin cassé
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UtilisateurRepository.class)
public class TodoListApplication {

	/**
	 * main de mon application spring boot
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

}
