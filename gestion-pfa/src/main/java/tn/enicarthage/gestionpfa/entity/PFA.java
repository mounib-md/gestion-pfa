package tn.enicarthage.gestionpfa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pfa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PFA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type; // ACADEMIQUE, EN_ENTREPRISE

    @Enumerated(EnumType.STRING)
    private Statut statut; // PROPOSE, VALIDE, EN_COURS, TERMINE, REFUSE

    @Column(name = "annee_universitaire")
    private String anneeUniversitaire; // ex: "2024-2025"

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    // Technologies / outils utilisés
    private String technologies;

    // Chemin vers le rapport PDF déposé
    @Column(name = "chemin_rapport")
    private String cheminRapport;

    // Note finale
    private Double note;

    // ─── Relations ────────────────────────────────────────────

    // Un PFA appartient à un seul étudiant
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Etudiant etudiant;

    // Un PFA est supervisé par un encadrant interne (enseignant)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encadrant_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Encadrant encadrant;

    // Un PFA peut être réalisé en entreprise (optionnel)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Entreprise entreprise;

    // ─── Énumérations ─────────────────────────────────────────

    public enum Type {
        ACADEMIQUE,
        EN_ENTREPRISE
    }

    public enum Statut {
        PROPOSE,
        VALIDE,
        EN_COURS,
        TERMINE,
        REFUSE
    }
}