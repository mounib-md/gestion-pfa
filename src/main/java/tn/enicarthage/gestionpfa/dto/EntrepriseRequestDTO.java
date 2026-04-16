package tn.enicarthage.gestionpfa.dto;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO utilisé pour les requêtes POST / PUT sur une Entreprise.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrepriseRequestDTO {

    @NotBlank(message = "Le nom de l'entreprise est obligatoire")
    private String nom;

    private String secteur;
    private String adresse;
    private String ville;
    private String pays;
    private String telephone;

    @Email(message = "Email invalide")
    private String email;

    private String siteWeb;

    // Tuteur en entreprise
    private String nomTuteur;
    private String prenomTuteur;

    @Email(message = "Email tuteur invalide")
    private String emailTuteur;

    private String telephoneTuteur;
    private String fonctionTuteur;
}