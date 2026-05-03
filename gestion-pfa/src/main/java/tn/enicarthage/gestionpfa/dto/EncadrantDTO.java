package tn.enicarthage.gestionpfa.dto;

import lombok.*;
import tn.enicarthage.gestionpfa.entity.Encadrant.Grade;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncadrantDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Grade grade;
    private String specialite;
    private String departement;
    private Integer quotaMax;

    // Nombre de PFA encadrés actuellement (calculé côté service)
    private int nombrePfaEncadres;
}