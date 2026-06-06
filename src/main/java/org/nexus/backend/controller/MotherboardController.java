package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.MotherboardDTO;
import org.nexus.backend.repository.MotherboardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/motherboards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MotherboardController {

    private final MotherboardRepository motherboardRepository;

    @GetMapping
    public List<MotherboardDTO> listar() {
        return motherboardRepository.findAll().stream()
                .map(MotherboardDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotherboardDTO> obtener(@PathVariable UUID id) {
        return motherboardRepository.findById(id)
                .map(MotherboardDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
