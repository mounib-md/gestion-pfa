package tn.enicarthage.gestionpfa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// TODO: Importer votre repository ici, par exemple :
// import tn.enicarthage.gestionpfa.repository.UtilisateurRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    // TODO: Décommenter et injecter votre repository pour chercher l'utilisateur
    // private final UtilisateurRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // L'email est utilisé comme "username" dans notre cas
            // TODO: Décommenter cette partie une fois votre Entité et Repository créés
            /*
            return repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + username));
            */
            
            // Ligne temporaire pour que le projet compile en attendant l'étape 2 :
            throw new UsernameNotFoundException("Repository non configuré");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // On indique à Spring Security d'utiliser notre UserDetailsService personnalisé
        authProvider.setUserDetailsService(userDetailsService());
        // On lui indique quel algorithme utiliser pour vérifier les mots de passe
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt est le standard actuel pour hacher les mots de passe de manière sécurisée
        return new BCryptPasswordEncoder();
    }
}