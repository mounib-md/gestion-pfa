package tn.enicarthage.gestionpfa.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tn.enicarthage.gestionpfa.entity.PFA.Statut;
import tn.enicarthage.gestionpfa.entity.PFA.Type;

import java.time.LocalDate;

/**
 * DTO utilisé pour les requêtes POST / PUT sur un PFA.
 * Contient uniquement les IDs des relations (pas les objets complets).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PFARequestDTO {

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    private String description;

    @NotNull(message = "Le type est obligatoire")
    private Type type;

    private Statut statut;

    @NotBlank(message = "L'année universitaire est obligatoire")
    private String anneeUniversitaire;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String technologies;

    @NotNull(message = "L'étudiant est obligatoire")
    private Long etudiantId;

    private Long encadrantId;

    // Optionnel — seulement si type = EN_ENTREPRISE
    private Long entrepriseId;
}