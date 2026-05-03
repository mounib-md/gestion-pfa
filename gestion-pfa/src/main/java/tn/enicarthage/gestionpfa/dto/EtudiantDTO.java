package tn.enicarthage.gestionpfa.dto;

import lombok.*;
import tn.enicarthage.gestionpfa.entity.Groupe;
import tn.enicarthage.gestionpfa.entity.Niveau;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private String numInscription;
    private String telephone;
    private Niveau niveau;
    private String specialite;
    private Groupe groupe;

    // Résumé du PFA associé (évite la récursion)
    private Long pfaId;
    private String pfaTitre;
}