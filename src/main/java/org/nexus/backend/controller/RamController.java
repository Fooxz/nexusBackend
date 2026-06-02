package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Ram;
import org.nexus.backend.repository.RamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rams")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RamController {

    private final RamRepository ramRepository;

    @GetMapping
    public List<Ram> listar() {
        return ramRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ram> obtener(@PathVariable UUID id) {
        return ramRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
