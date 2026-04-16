package tn.enicarthage.gestionpfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.enicarthage.gestionpfa.entity.Etudiant;
import tn.enicarthage.gestionpfa.entity.Niveau;
import tn.enicarthage.gestionpfa.entity.Groupe;
import tn.enicarthage.gestionpfa.exception.DuplicateResourceException;
import tn.enicarthage.gestionpfa.exception.ResourceNotFoundException;
import tn.enicarthage.gestionpfa.repository.EtudiantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public Etudiant findById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etudiant introuvable avec l'id : " + id));
    }

    public Etudiant findByEmail(String email) {
        return etudiantRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Etudiant introuvable avec l'email : " + email));
    }

    public Etudiant save(Etudiant etudiant) {
        if (etudiantRepository.findByEmail(etudiant.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Un étudiant avec cet email existe déjà : " + etudiant.getEmail());
        }
        return etudiantRepository.save(etudiant);
    }

    public Etudiant update(Long id, Etudiant etudiantModifie) {
        Etudiant existant = findById(id);
        existant.setNom(etudiantModifie.getNom());
        existant.setPrenom(etudiantModifie.getPrenom());
        existant.setEmail(etudiantModifie.getEmail());
        existant.setCin(etudiantModifie.getCin());
        existant.setTelephone(etudiantModifie.getTelephone());
        existant.setNiveau(etudiantModifie.getNiveau());
        existant.setSpecialite(etudiantModifie.getSpecialite());
        existant.setGroupe(etudiantModifie.getGroupe());
        return etudiantRepository.save(existant);
    }

    public void delete(Long id) {
        findById(id);
        etudiantRepository.deleteById(id);
    }

    public List<Etudiant> findByNiveau(Niveau niveau) {
        return etudiantRepository.findByNiveau(niveau);
    }

    public List<Etudiant> findByGroupe(Groupe groupe) {
        return etudiantRepository.findByGroupe(groupe);
    }

    public List<Etudiant> findEtudiantsSansPFA() {
        return etudiantRepository.findEtudiantsSansPFA();
    }

    public List<Etudiant> rechercher(String motCle) {
        return etudiantRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(motCle, motCle);
    }
}