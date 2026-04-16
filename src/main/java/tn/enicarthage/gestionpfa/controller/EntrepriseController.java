package tn.enicarthage.gestionpfa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.gestionpfa.dto.EntrepriseDTO;
import tn.enicarthage.gestionpfa.dto.EntrepriseRequestDTO;
import tn.enicarthage.gestionpfa.entity.Entreprise;
import tn.enicarthage.gestionpfa.mapper.EntrepriseMapper;
import tn.enicarthage.gestionpfa.service.EntrepriseService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entreprises")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    // GET /api/entreprises
    @GetMapping
    public ResponseEntity<List<EntrepriseDTO>> getAll() {
        List<EntrepriseDTO> dtos = entrepriseService.findAll()
                .stream()
                .map(EntrepriseMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/entreprises/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EntrepriseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EntrepriseMapper.toDTO(entrepriseService.findById(id)));
    }

    // GET /api/entreprises/search?motCle=telnet
    @GetMapping("/search")
    public ResponseEntity<List<EntrepriseDTO>> search(@RequestParam String motCle) {
        List<EntrepriseDTO> dtos = entrepriseService.rechercher(motCle)
                .stream()
                .map(EntrepriseMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/entreprises/ville/{ville}
    @GetMapping("/ville/{ville}")
    public ResponseEntity<List<EntrepriseDTO>> getByVille(@PathVariable String ville) {
        List<EntrepriseDTO> dtos = entrepriseService.findByVille(ville)
                .stream()
                .map(EntrepriseMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // POST /api/entreprises
    @PostMapping
    public ResponseEntity<EntrepriseDTO> create(@Valid @RequestBody EntrepriseRequestDTO dto) {
        Entreprise entreprise = EntrepriseMapper.toEntity(dto);
        Entreprise saved = entrepriseService.save(entreprise);
        return ResponseEntity.status(HttpStatus.CREATED).body(EntrepriseMapper.toDTO(saved));
    }

    // PUT /api/entreprises/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EntrepriseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody EntrepriseRequestDTO dto) {
        Entreprise entreprise = EntrepriseMapper.toEntity(dto);
        Entreprise updated = entrepriseService.update(id, entreprise);
        return ResponseEntity.ok(EntrepriseMapper.toDTO(updated));
    }

    // DELETE /api/entreprises/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}