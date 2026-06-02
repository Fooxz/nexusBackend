package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Psu;
import org.nexus.backend.repository.PsuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/psus")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PsuController {

    private final PsuRepository psuRepository;

    @GetMapping
    public List<Psu> listar() {
        return psuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Psu> obtener(@PathVariable UUID id) {
        return psuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
