package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.CaseDTO;
import org.nexus.backend.repository.CaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CaseController {

    private final CaseRepository caseRepository;

    @GetMapping
    public List<CaseDTO> listar() {
        return caseRepository.findAll().stream()
                .map(CaseDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDTO> obtener(@PathVariable UUID id) {
        return caseRepository.findById(id)
                .map(CaseDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
