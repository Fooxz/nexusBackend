package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.CoolingDTO;
import org.nexus.backend.repository.CoolingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coolings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CoolingController {

    private final CoolingRepository coolingRepository;

    @GetMapping
    public List<CoolingDTO> listar() {
        return coolingRepository.findAll().stream()
                .map(CoolingDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoolingDTO> obtener(@PathVariable UUID id) {
        return coolingRepository.findById(id)
                .map(CoolingDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
