package tn.enicarthage.gestionpfa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.gestionpfa.dto.EtudiantDTO;
import tn.enicarthage.gestionpfa.dto.EtudiantRequestDTO;
import tn.enicarthage.gestionpfa.entity.Etudiant;
import tn.enicarthage.gestionpfa.entity.Groupe;
import tn.enicarthage.gestionpfa.entity.Niveau;
import tn.enicarthage.gestionpfa.mapper.EtudiantMapper;
import tn.enicarthage.gestionpfa.service.EtudiantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;

    // GET /api/etudiants
    @GetMapping
    public ResponseEntity<List<EtudiantDTO>> getAll() {
        List<EtudiantDTO> dtos = etudiantService.findAll()
                .stream()
                .map(EtudiantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/etudiants/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EtudiantMapper.toDTO(etudiantService.findById(id)));
    }

    // GET /api/etudiants/search?motCle=ahmed
    @GetMapping("/search")
    public ResponseEntity<List<EtudiantDTO>> search(@RequestParam String motCle) {
        List<EtudiantDTO> dtos = etudiantService.rechercher(motCle)
                .stream()
                .map(EtudiantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/etudiants/niveau/MASTER_1
    @GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<EtudiantDTO>> getByNiveau(@PathVariable Niveau niveau) {
        List<EtudiantDTO> dtos = etudiantService.findByNiveau(niveau)
                .stream()
                .map(EtudiantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/etudiants/groupe/A
    @GetMapping("/groupe/{groupe}")
    public ResponseEntity<List<EtudiantDTO>> getByGroupe(@PathVariable Groupe groupe) {
        List<EtudiantDTO> dtos = etudiantService.findByGroupe(groupe)
                .stream()
                .map(EtudiantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/etudiants/sans-pfa
    @GetMapping("/sans-pfa")
    public ResponseEntity<List<EtudiantDTO>> getSansPFA() {
        List<EtudiantDTO> dtos = etudiantService.findEtudiantsSansPFA()
                .stream()
                .map(EtudiantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // POST /api/etudiants
    @PostMapping
    public ResponseEntity<EtudiantDTO> create(@Valid @RequestBody EtudiantRequestDTO dto) {
        Etudiant etudiant = EtudiantMapper.toEntity(dto);
        Etudiant saved = etudiantService.save(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(EtudiantMapper.toDTO(saved));
    }

    // PUT /api/etudiants/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody EtudiantRequestDTO dto) {
        Etudiant etudiant = EtudiantMapper.toEntity(dto);
        Etudiant updated = etudiantService.update(id, etudiant);
        return ResponseEntity.ok(EtudiantMapper.toDTO(updated));
    }

    // DELETE /api/etudiants/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etudiantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}