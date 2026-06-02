package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Celular;
import org.nexus.backend.repository.CelularRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/celulares")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CelularController {

    private final CelularRepository celularRepository;

    @GetMapping
    public List<Celular> listar() {
        return celularRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Celular> obtener(@PathVariable UUID id) {
        return celularRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
