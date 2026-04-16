package tn.enicarthage.gestionpfa.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tn.enicarthage.gestionpfa.entity.Groupe;
import tn.enicarthage.gestionpfa.entity.Niveau;

/**
 * DTO utilisé pour les requêtes POST / PUT sur un Etudiant.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    private String cin;
    private String numInscription;
    private String telephone;

    @NotNull(message = "Le niveau est obligatoire")
    private Niveau niveau;

    private String specialite;
    private Groupe groupe;
}