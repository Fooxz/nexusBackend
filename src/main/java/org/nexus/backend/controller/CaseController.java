package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Case;
import org.nexus.backend.repository.CaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CaseController {

    private final CaseRepository caseRepository;

    @GetMapping
    public List<Case> listar() {
        return caseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Case> obtener(@PathVariable UUID id) {
        return caseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
