package tn.enicarthage.gestionpfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.enicarthage.gestionpfa.entity.Encadrant;
import tn.enicarthage.gestionpfa.entity.Entreprise;
import tn.enicarthage.gestionpfa.entity.PFA;
import tn.enicarthage.gestionpfa.exception.DuplicateResourceException;
import tn.enicarthage.gestionpfa.exception.ResourceNotFoundException;
import tn.enicarthage.gestionpfa.repository.PFARepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PFAService {

    private final PFARepository pfaRepository;
    private final EncadrantService encadrantService;
    private final EntrepriseService entrepriseService;

    public List<PFA> findAll() {
        return pfaRepository.findAll();
    }

    public PFA findById(Long id) {
        return pfaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PFA introuvable avec l'id : " + id));
    }

    public PFA findByEtudiantId(Long etudiantId) {
        return pfaRepository.findByEtudiantId(etudiantId)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun PFA trouvé pour l'étudiant id : " + etudiantId));
    }

    @Transactional
    public PFA save(PFA pfa) {
        pfaRepository.findByEtudiantId(pfa.getEtudiant().getId()).ifPresent(p -> {
            throw new DuplicateResourceException("Cet étudiant a déjà un PFA affecté.");
        });
        pfa.setStatut(PFA.Statut.PROPOSE);
        return pfaRepository.save(pfa);
    }

    @Transactional
    public PFA update(Long id, PFA pfaModifie) {
        PFA existant = findById(id);
        existant.setTitre(pfaModifie.getTitre());
        existant.setDescription(pfaModifie.getDescription());
        existant.setType(pfaModifie.getType());
        existant.setAnneeUniversitaire(pfaModifie.getAnneeUniversitaire());
        existant.setDateDebut(pfaModifie.getDateDebut());
        existant.setDateFin(pfaModifie.getDateFin());
        existant.setTechnologies(pfaModifie.getTechnologies());
        return pfaRepository.save(existant);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        pfaRepository.deleteById(id);
    }

    @Transactional
    public PFA affecterEncadrant(Long pfaId, Long encadrantId) {
        PFA pfa = findById(pfaId);
        if (!encadrantService.isDisponible(encadrantId)) {
            throw new RuntimeException("L'encadrant a atteint son quota maximum de PFA.");
        }
        Encadrant encadrant = encadrantService.findById(encadrantId);
        pfa.setEncadrant(encadrant);
        return pfaRepository.save(pfa);
    }

    @Transactional
    public PFA affecterEntreprise(Long pfaId, Long entrepriseId) {
        PFA pfa = findById(pfaId);
        Entreprise entreprise = entrepriseService.findById(entrepriseId);
        pfa.setEntreprise(entreprise);
        pfa.setType(PFA.Type.EN_ENTREPRISE);
        return pfaRepository.save(pfa);
    }

    @Transactional
    public PFA changerStatut(Long pfaId, PFA.Statut nouveauStatut) {
        PFA pfa = findById(pfaId);
        pfa.setStatut(nouveauStatut);
        return pfaRepository.save(pfa);
    }

    @Transactional
    public PFA valider(Long pfaId) {
        PFA pfa = findById(pfaId);
        if (pfa.getStatut() != PFA.Statut.PROPOSE) {
            throw new RuntimeException("Seul un PFA avec statut PROPOSE peut être validé.");
        }
        if (pfa.getEncadrant() == null) {
            throw new RuntimeException("Un encadrant doit être affecté avant la validation.");
        }
        pfa.setStatut(PFA.Statut.VALIDE);
        return pfaRepository.save(pfa);
    }

    @Transactional
    public PFA deposerRapport(Long pfaId, String cheminRapport) {
        PFA pfa = findById(pfaId);
        pfa.setCheminRapport(cheminRapport);
        pfa.setStatut(PFA.Statut.TERMINE);
        return pfaRepository.save(pfa);
    }

    @Transactional
    public PFA noterPFA(Long pfaId, Double note) {
        if (note < 0 || note > 20) {
            throw new RuntimeException("La note doit être entre 0 et 20.");
        }
        PFA pfa = findById(pfaId);
        if (pfa.getStatut() != PFA.Statut.TERMINE) {
            throw new RuntimeException("Le PFA doit être terminé avant d'être noté.");
        }
        pfa.setNote(note);
        return pfaRepository.save(pfa);
    }

    public List<PFA> findByStatut(PFA.Statut statut) {
        return pfaRepository.findByStatut(statut);
    }

    public List<PFA> findByAnnee(String annee) {
        return pfaRepository.findByAnneeUniversitaire(annee);
    }

    public List<PFA> findActifs() {
        return pfaRepository.findPFAActifs();
    }

    public List<PFA> rechercher(String motCle) {
        return pfaRepository.findByTitreContainingIgnoreCaseOrDescriptionContainingIgnoreCase(motCle, motCle);
    }

    public Map<String, Long> statistiquesParStatut() {
        Map<String, Long> stats = new HashMap<>();
        List<Object[]> results = pfaRepository.countByStatut();
        for (Object[] row : results) {
            stats.put(row[0].toString(), (Long) row[1]);
        }
        return stats;
    }
}