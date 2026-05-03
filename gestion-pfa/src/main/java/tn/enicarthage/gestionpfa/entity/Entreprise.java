package tn.enicarthage.gestionpfa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "entreprise")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String secteur;

    private String adresse;

    private String ville;

    private String pays;

    private String telephone;

    private String email;

    private String siteWeb;

    // Nom du tuteur en entreprise
    @Column(name = "nom_tuteur")
    private String nomTuteur;

    @Column(name = "prenom_tuteur")
    private String prenomTuteur;

    @Column(name = "email_tuteur")
    private String emailTuteur;

    @Column(name = "telephone_tuteur")
    private String telephoneTuteur;

    @Column(name = "fonction_tuteur")
    private String fonctionTuteur;

    // Relation : une entreprise peut accueillir plusieurs PFA
    @OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PFA> pfas;
}