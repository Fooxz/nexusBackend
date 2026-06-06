package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.CpuDTO;
import org.nexus.backend.repository.CpuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cpus")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CpuController {

    private final CpuRepository cpuRepository;

    @GetMapping
    public List<CpuDTO> listar() {
        return cpuRepository.findAll().stream()
                .map(CpuDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CpuDTO> obtener(@PathVariable UUID id) {
        return cpuRepository.findById(id)
                .map(CpuDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
