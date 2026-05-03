package tn.enicarthage.gestionpfa.mapper;

import tn.enicarthage.gestionpfa.dto.EntrepriseDTO;
import tn.enicarthage.gestionpfa.dto.EntrepriseRequestDTO;
import tn.enicarthage.gestionpfa.entity.Entreprise;

public class EntrepriseMapper {

    public static EntrepriseDTO toDTO(Entreprise e) {
        if (e == null) return null;
        return EntrepriseDTO.builder()
                .id(e.getId())
                .nom(e.getNom())
                .secteur(e.getSecteur())
                .adresse(e.getAdresse())
                .ville(e.getVille())
                .pays(e.getPays())
                .telephone(e.getTelephone())
                .email(e.getEmail())
                .siteWeb(e.getSiteWeb())
                .nomTuteur(e.getNomTuteur())
                .prenomTuteur(e.getPrenomTuteur())
                .emailTuteur(e.getEmailTuteur())
                .telephoneTuteur(e.getTelephoneTuteur())
                .fonctionTuteur(e.getFonctionTuteur())
                .build();
    }

    public static Entreprise toEntity(EntrepriseRequestDTO dto) {
        if (dto == null) return null;
        return Entreprise.builder()
                .nom(dto.getNom())
                .secteur(dto.getSecteur())
                .adresse(dto.getAdresse())
                .ville(dto.getVille())
                .pays(dto.getPays())
                .telephone(dto.getTelephone())
                .email(dto.getEmail())
                .siteWeb(dto.getSiteWeb())
                .nomTuteur(dto.getNomTuteur())
                .prenomTuteur(dto.getPrenomTuteur())
                .emailTuteur(dto.getEmailTuteur())
                .telephoneTuteur(dto.getTelephoneTuteur())
                .fonctionTuteur(dto.getFonctionTuteur())
                .build();
    }
}