package tn.enicarthage.gestionpfa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.gestionpfa.dto.EncadrantDTO;
import tn.enicarthage.gestionpfa.dto.EncadrantRequestDTO;
import tn.enicarthage.gestionpfa.entity.Encadrant;
import tn.enicarthage.gestionpfa.mapper.EncadrantMapper;
import tn.enicarthage.gestionpfa.service.EncadrantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/encadrants")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EncadrantController {

    private final EncadrantService encadrantService;

    // GET /api/encadrants
    @GetMapping
    public ResponseEntity<List<EncadrantDTO>> getAll() {
        List<EncadrantDTO> dtos = encadrantService.findAll()
                .stream()
                .map(EncadrantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/encadrants/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EncadrantDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EncadrantMapper.toDTO(encadrantService.findById(id)));
    }

    // GET /api/encadrants/disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<EncadrantDTO>> getDisponibles() {
        List<EncadrantDTO> dtos = encadrantService.findAll()
                .stream()
                .filter(e -> encadrantService.isDisponible(e.getId()))
                .map(EncadrantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/encadrants/search?motCle=trabelsi
    @GetMapping("/search")
    public ResponseEntity<List<EncadrantDTO>> search(@RequestParam String motCle) {
        List<EncadrantDTO> dtos = encadrantService.rechercher(motCle)
                .stream()
                .map(EncadrantMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // POST /api/encadrants
    @PostMapping
    public ResponseEntity<EncadrantDTO> create(@Valid @RequestBody EncadrantRequestDTO dto) {
        Encadrant encadrant = EncadrantMapper.toEntity(dto);
        Encadrant saved = encadrantService.save(encadrant);
        return ResponseEntity.status(HttpStatus.CREATED).body(EncadrantMapper.toDTO(saved));
    }

    // PUT /api/encadrants/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EncadrantDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody EncadrantRequestDTO dto) {
        Encadrant encadrant = EncadrantMapper.toEntity(dto);
        Encadrant updated = encadrantService.update(id, encadrant);
        return ResponseEntity.ok(EncadrantMapper.toDTO(updated));
    }

    // DELETE /api/encadrants/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        encadrantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}