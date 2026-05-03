package tn.enicarthage.gestionpfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.enicarthage.gestionpfa.entity.Entreprise;
import tn.enicarthage.gestionpfa.exception.ResourceNotFoundException;
import tn.enicarthage.gestionpfa.repository.EntrepriseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }

    public Entreprise findById(Long id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise introuvable avec l'id : " + id));
    }

    public Entreprise save(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    public Entreprise update(Long id, Entreprise entrepriseModifiee) {
        Entreprise existante = findById(id);
        existante.setNom(entrepriseModifiee.getNom());
        existante.setSecteur(entrepriseModifiee.getSecteur());
        existante.setAdresse(entrepriseModifiee.getAdresse());
        existante.setVille(entrepriseModifiee.getVille());
        existante.setPays(entrepriseModifiee.getPays());
        existante.setTelephone(entrepriseModifiee.getTelephone());
        existante.setEmail(entrepriseModifiee.getEmail());
        existante.setSiteWeb(entrepriseModifiee.getSiteWeb());
        existante.setNomTuteur(entrepriseModifiee.getNomTuteur());
        existante.setPrenomTuteur(entrepriseModifiee.getPrenomTuteur());
        existante.setEmailTuteur(entrepriseModifiee.getEmailTuteur());
        existante.setTelephoneTuteur(entrepriseModifiee.getTelephoneTuteur());
        existante.setFonctionTuteur(entrepriseModifiee.getFonctionTuteur());
        return entrepriseRepository.save(existante);
    }

    public void delete(Long id) {
        findById(id);
        entrepriseRepository.deleteById(id);
    }

    public List<Entreprise> findBySecteur(String secteur) {
        return entrepriseRepository.findBySecteurContainingIgnoreCase(secteur);
    }

    public List<Entreprise> findByVille(String ville) {
        return entrepriseRepository.findByVilleIgnoreCase(ville);
    }

    public List<Entreprise> rechercher(String motCle) {
        return entrepriseRepository.findByNomContainingIgnoreCase(motCle);
    }
}