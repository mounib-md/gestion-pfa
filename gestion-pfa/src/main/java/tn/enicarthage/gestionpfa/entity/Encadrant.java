package tn.enicarthage.gestionpfa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "encadrant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Encadrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    private String telephone;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade; // ASSISTANT, MAITRE_ASSISTANT, MAITRE_CONFERENCE, PROFESSEUR

    private String specialite;

    private String departement;

    
    @Column(name = "quota_max")
    private Integer quotaMax;

    
    @OneToMany(mappedBy = "encadrant", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PFA> pfas;

    public enum Grade {
        ASSISTANT,
        MAITRE_ASSISTANT,
        MAITRE_CONFERENCE,
        PROFESSEUR
    }
}