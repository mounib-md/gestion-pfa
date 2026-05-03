package tn.enicarthage.gestionpfa.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrepriseDTO {

    private Long id;
    private String nom;
    private String secteur;
    private String adresse;
    private String ville;
    private String pays;
    private String telephone;
    private String email;
    private String siteWeb;

    // Informations tuteur
    private String nomTuteur;
    private String prenomTuteur;
    private String emailTuteur;
    private String telephoneTuteur;
    private String fonctionTuteur;
}