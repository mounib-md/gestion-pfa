package tn.enicarthage.gestionpfa.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tn.enicarthage.gestionpfa.entity.Encadrant.Grade;

/**
 * DTO utilisé pour les requêtes POST / PUT sur un Encadrant.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncadrantRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    private String telephone;
    private Grade grade;
    private String specialite;
    private String departement;

    @Min(value = 1, message = "Le quota doit être au moins 1")
    @Max(value = 20, message = "Le quota ne peut pas dépasser 20")
    private Integer quotaMax;
}