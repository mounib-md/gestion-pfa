package tn.enicarthage.gestionpfa.mapper;

import tn.enicarthage.gestionpfa.dto.EtudiantDTO;
import tn.enicarthage.gestionpfa.dto.EtudiantRequestDTO;
import tn.enicarthage.gestionpfa.entity.Etudiant;

public class EtudiantMapper {

    // Entité → DTO (réponse)
    public static EtudiantDTO toDTO(Etudiant e) {
        if (e == null) return null;
        return EtudiantDTO.builder()
                .id(e.getId())
                .nom(e.getNom())
                .prenom(e.getPrenom())
                .email(e.getEmail())
                .cin(e.getCin())
                .numInscription(e.getNumInscription())
                .telephone(e.getTelephone())
                .niveau(e.getNiveau())
                .specialite(e.getSpecialite())
                .groupe(e.getGroupe())
                .pfaId(e.getPfa() != null ? e.getPfa().getId() : null)
                .pfaTitre(e.getPfa() != null ? e.getPfa().getTitre() : null)
                .build();
    }

    // RequestDTO → Entité (création)
    public static Etudiant toEntity(EtudiantRequestDTO dto) {
        if (dto == null) return null;
        return Etudiant.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .cin(dto.getCin())
                .numInscription(dto.getNumInscription())
                .telephone(dto.getTelephone())
                .niveau(dto.getNiveau())
                .specialite(dto.getSpecialite())
                .groupe(dto.getGroupe())
                .build();
    }
}