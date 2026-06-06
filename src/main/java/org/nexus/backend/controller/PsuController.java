package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.PsuDTO;
import org.nexus.backend.repository.PsuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/psus")
@RequiredArgsConstructor
public class PsuController {

    private final PsuRepository psuRepository;

    @GetMapping
    public List<PsuDTO> listar() {
        return psuRepository.findAll().stream()
                .map(PsuDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsuDTO> obtener(@PathVariable UUID id) {
        return psuRepository.findById(id)
                .map(PsuDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
