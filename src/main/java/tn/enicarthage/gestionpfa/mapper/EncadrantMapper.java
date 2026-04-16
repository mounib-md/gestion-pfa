package tn.enicarthage.gestionpfa.mapper;

import tn.enicarthage.gestionpfa.dto.EncadrantDTO;
import tn.enicarthage.gestionpfa.dto.EncadrantRequestDTO;
import tn.enicarthage.gestionpfa.entity.Encadrant;

public class EncadrantMapper {

    public static EncadrantDTO toDTO(Encadrant e) {
        if (e == null) return null;
        return EncadrantDTO.builder()
                .id(e.getId())
                .nom(e.getNom())
                .prenom(e.getPrenom())
                .email(e.getEmail())
                .telephone(e.getTelephone())
                .grade(e.getGrade())
                .specialite(e.getSpecialite())
                .departement(e.getDepartement())
                .quotaMax(e.getQuotaMax())
                .nombrePfaEncadres(e.getPfas() != null ? e.getPfas().size() : 0)
                .build();
    }

    public static Encadrant toEntity(EncadrantRequestDTO dto) {
        if (dto == null) return null;
        return Encadrant.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .grade(dto.getGrade())
                .specialite(dto.getSpecialite())
                .departement(dto.getDepartement())
                .quotaMax(dto.getQuotaMax())
                .build();
    }
}