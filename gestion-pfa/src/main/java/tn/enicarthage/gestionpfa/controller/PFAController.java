package tn.enicarthage.gestionpfa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.gestionpfa.dto.PFADTO;
import tn.enicarthage.gestionpfa.dto.PFARequestDTO;
import tn.enicarthage.gestionpfa.entity.Encadrant;
import tn.enicarthage.gestionpfa.entity.Entreprise;
import tn.enicarthage.gestionpfa.entity.Etudiant;
import tn.enicarthage.gestionpfa.entity.PFA;
import tn.enicarthage.gestionpfa.mapper.PFAMapper;
import tn.enicarthage.gestionpfa.service.EncadrantService;
import tn.enicarthage.gestionpfa.service.EntrepriseService;
import tn.enicarthage.gestionpfa.service.EtudiantService;
import tn.enicarthage.gestionpfa.service.PFAService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pfas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PFAController {

    private final PFAService pfaService;
    private final EtudiantService etudiantService;
    private final EncadrantService encadrantService;
    private final EntrepriseService entrepriseService;

    // GET /api/pfas
    @GetMapping
    public ResponseEntity<List<PFADTO>> getAll() {
        List<PFADTO> dtos = pfaService.findAll()
                .stream()
                .map(PFAMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/pfas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PFADTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.findById(id)));
    }

    // GET /api/pfas/etudiant/{etudiantId}
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<PFADTO> getByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.findByEtudiantId(etudiantId)));
    }

    // GET /api/pfas/search?motCle=application
    @GetMapping("/search")
    public ResponseEntity<List<PFADTO>> search(@RequestParam String motCle) {
        List<PFADTO> dtos = pfaService.rechercher(motCle)
                .stream()
                .map(PFAMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/pfas/statut/EN_COURS
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<PFADTO>> getByStatut(@PathVariable PFA.Statut statut) {
        List<PFADTO> dtos = pfaService.findByStatut(statut)
                .stream()
                .map(PFAMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/pfas/annee/2024-2025
    @GetMapping("/annee/{annee}")
    public ResponseEntity<List<PFADTO>> getByAnnee(@PathVariable String annee) {
        List<PFADTO> dtos = pfaService.findByAnnee(annee)
                .stream()
                .map(PFAMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/pfas/actifs
    @GetMapping("/actifs")
    public ResponseEntity<List<PFADTO>> getActifs() {
        List<PFADTO> dtos = pfaService.findActifs()
                .stream()
                .map(PFAMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // POST /api/pfas
    @PostMapping
    public ResponseEntity<PFADTO> create(@Valid @RequestBody PFARequestDTO dto) {
        // Résoudre les relations via leurs IDs
        Etudiant etudiant = etudiantService.findById(dto.getEtudiantId());
        Encadrant encadrant = dto.getEncadrantId() != null
                ? encadrantService.findById(dto.getEncadrantId()) : null;
        Entreprise entreprise = dto.getEntrepriseId() != null
                ? entrepriseService.findById(dto.getEntrepriseId()) : null;

        PFA pfa = PFA.builder()
                .titre(dto.getTitre())
                .description(dto.getDescription())
                .type(dto.getType())
                .statut(dto.getStatut() != null ? dto.getStatut() : PFA.Statut.PROPOSE)
                .anneeUniversitaire(dto.getAnneeUniversitaire())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .technologies(dto.getTechnologies())
                .etudiant(etudiant)
                .encadrant(encadrant)
                .entreprise(entreprise)
                .build();

        PFA saved = pfaService.save(pfa);
        return ResponseEntity.status(HttpStatus.CREATED).body(PFAMapper.toDTO(saved));
    }

    // PUT /api/pfas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PFADTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PFARequestDTO dto) {
        PFA pfaModifie = PFA.builder()
                .titre(dto.getTitre())
                .description(dto.getDescription())
                .type(dto.getType())
                .anneeUniversitaire(dto.getAnneeUniversitaire())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .technologies(dto.getTechnologies())
                .build();
        PFA updated = pfaService.update(id, pfaModifie);
        return ResponseEntity.ok(PFAMapper.toDTO(updated));
    }

    // DELETE /api/pfas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pfaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ─── Actions métier ────────────────────────────────────────

    @PutMapping("/{id}/encadrant/{encadrantId}")
    public ResponseEntity<PFADTO> affecterEncadrant(
            @PathVariable Long id, @PathVariable Long encadrantId) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.affecterEncadrant(id, encadrantId)));
    }

    @PutMapping("/{id}/entreprise/{entrepriseId}")
    public ResponseEntity<PFADTO> affecterEntreprise(
            @PathVariable Long id, @PathVariable Long entrepriseId) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.affecterEntreprise(id, entrepriseId)));
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<PFADTO> valider(@PathVariable Long id) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.valider(id)));
    }

    @PutMapping("/{id}/statut/{statut}")
    public ResponseEntity<PFADTO> changerStatut(
            @PathVariable Long id, @PathVariable PFA.Statut statut) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.changerStatut(id, statut)));
    }

    @PutMapping("/{id}/rapport")
    public ResponseEntity<PFADTO> deposerRapport(
            @PathVariable Long id, @RequestParam String cheminRapport) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.deposerRapport(id, cheminRapport)));
    }

    @PutMapping("/{id}/note")
    public ResponseEntity<PFADTO> noter(
            @PathVariable Long id, @RequestParam Double note) {
        return ResponseEntity.ok(PFAMapper.toDTO(pfaService.noterPFA(id, note)));
    }

    // GET /api/pfas/stats
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(pfaService.statistiquesParStatut());
    }
}