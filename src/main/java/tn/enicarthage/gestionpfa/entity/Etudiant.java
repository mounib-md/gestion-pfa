package tn.enicarthage.gestionpfa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etudiant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String cin;

    @Column(name = "num_inscription", unique = true)
    private String numInscription;

    private String telephone;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    private String specialite;

    @Enumerated(EnumType.STRING)
    private Groupe groupe;

    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PFA pfa;
}