package tn.enicarthage.gestionpfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.enicarthage.gestionpfa.entity.Encadrant;
import tn.enicarthage.gestionpfa.exception.DuplicateResourceException;
import tn.enicarthage.gestionpfa.exception.ResourceNotFoundException;
import tn.enicarthage.gestionpfa.repository.EncadrantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EncadrantService {

    private final EncadrantRepository encadrantRepository;

    public List<Encadrant> findAll() {
        return encadrantRepository.findAll();
    }

    public Encadrant findById(Long id) {
        return encadrantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encadrant introuvable avec l'id : " + id));
    }

    @Transactional
    public Encadrant save(Encadrant encadrant) {
        if (encadrantRepository.findByEmail(encadrant.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Un encadrant avec cet email existe déjà : " + encadrant.getEmail());
        }
        if (encadrant.getQuotaMax() == null) {
            encadrant.setQuotaMax(5);
        }
        return encadrantRepository.save(encadrant);
    }

    @Transactional
    public Encadrant update(Long id, Encadrant encadrantModifie) {
        Encadrant existant = findById(id);
        existant.setNom(encadrantModifie.getNom());
        existant.setPrenom(encadrantModifie.getPrenom());
        existant.setEmail(encadrantModifie.getEmail());
        existant.setTelephone(encadrantModifie.getTelephone());
        existant.setGrade(encadrantModifie.getGrade());
        existant.setSpecialite(encadrantModifie.getSpecialite());
        existant.setDepartement(encadrantModifie.getDepartement());
        existant.setQuotaMax(encadrantModifie.getQuotaMax());
        return encadrantRepository.save(existant);
    }

    @Transactional
    public void delete(Long id) {
        if (!encadrantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Encadrant introuvable avec l'id : " + id);
        }
        encadrantRepository.deleteById(id);
    }

    public List<Encadrant> findDisponibles() {
        return encadrantRepository.findEncadrantsDisponibles();
    }

    public boolean isDisponible(Long encadrantId) {
        Encadrant encadrant = findById(encadrantId);
        long nbPFAs = encadrantRepository.countPFAsByEncadrant(encadrantId);
        return nbPFAs < encadrant.getQuotaMax();
    }

    public List<Encadrant> findByDepartement(String departement) {
        return encadrantRepository.findByDepartement(departement);
    }

    public List<Encadrant> rechercher(String motCle) {
        if (motCle == null || motCle.trim().isEmpty()) {
            return findAll();
        }
        String motCleLower = motCle.toLowerCase();
        return encadrantRepository.findAll().stream()
                .filter(e -> (e.getNom() != null && e.getNom().toLowerCase().contains(motCleLower)) ||
                             (e.getPrenom() != null && e.getPrenom().toLowerCase().contains(motCleLower)))
                .collect(Collectors.toList());
    }
}