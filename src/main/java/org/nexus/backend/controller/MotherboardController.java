package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Motherboard;
import org.nexus.backend.repository.MotherboardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/motherboards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MotherboardController {

    private final MotherboardRepository motherboardRepository;

    @GetMapping
    public List<Motherboard> listar() {
        return motherboardRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motherboard> obtener(@PathVariable UUID id) {
        return motherboardRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
