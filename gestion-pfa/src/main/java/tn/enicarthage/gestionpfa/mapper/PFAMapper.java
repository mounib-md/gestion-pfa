package tn.enicarthage.gestionpfa.mapper;

import tn.enicarthage.gestionpfa.dto.PFADTO;
import tn.enicarthage.gestionpfa.entity.PFA;

public class PFAMapper {

    public static PFADTO toDTO(PFA p) {
        if (p == null) return null;
        return PFADTO.builder()
                .id(p.getId())
                .titre(p.getTitre())
                .description(p.getDescription())
                .type(p.getType())
                .statut(p.getStatut())
                .anneeUniversitaire(p.getAnneeUniversitaire())
                .dateDebut(p.getDateDebut())
                .dateFin(p.getDateFin())
                .technologies(p.getTechnologies())
                .cheminRapport(p.getCheminRapport())
                .note(p.getNote())
                // Étudiant
                .etudiantId(p.getEtudiant() != null ? p.getEtudiant().getId() : null)
                .etudiantNom(p.getEtudiant() != null ? p.getEtudiant().getNom() : null)
                .etudiantPrenom(p.getEtudiant() != null ? p.getEtudiant().getPrenom() : null)
                .etudiantEmail(p.getEtudiant() != null ? p.getEtudiant().getEmail() : null)
                // Encadrant
                .encadrantId(p.getEncadrant() != null ? p.getEncadrant().getId() : null)
                .encadrantNom(p.getEncadrant() != null ? p.getEncadrant().getNom() : null)
                .encadrantPrenom(p.getEncadrant() != null ? p.getEncadrant().getPrenom() : null)
                // Entreprise
                .entrepriseId(p.getEntreprise() != null ? p.getEntreprise().getId() : null)
                .entrepriseNom(p.getEntreprise() != null ? p.getEntreprise().getNom() : null)
                .build();
    }
}