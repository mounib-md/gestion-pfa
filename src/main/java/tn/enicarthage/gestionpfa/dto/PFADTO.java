package tn.enicarthage.gestionpfa.dto;

import lombok.*;
import tn.enicarthage.gestionpfa.entity.PFA.Statut;
import tn.enicarthage.gestionpfa.entity.PFA.Type;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PFADTO {

    private Long id;
    private String titre;
    private String description;
    private Type type;
    private Statut statut;
    private String anneeUniversitaire;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String technologies;
    private String cheminRapport;
    private Double note;

    // Étudiant associé (résumé — pas l'objet complet)
    private Long etudiantId;
    private String etudiantNom;
    private String etudiantPrenom;
    private String etudiantEmail;

    // Encadrant associé (résumé)
    private Long encadrantId;
    private String encadrantNom;
    private String encadrantPrenom;

    // Entreprise associée (optionnel, résumé)
    private Long entrepriseId;
    private String entrepriseNom;
}