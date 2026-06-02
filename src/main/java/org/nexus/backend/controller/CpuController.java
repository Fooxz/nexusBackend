package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Cpu;
import org.nexus.backend.repository.CpuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cpus")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CpuController {

    private final CpuRepository cpuRepository;

    @GetMapping
    public List<Cpu> listar() {
        return cpuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cpu> obtener(@PathVariable UUID id) {
        return cpuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
