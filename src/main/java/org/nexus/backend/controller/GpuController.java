package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Gpu;
import org.nexus.backend.repository.GpuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gpus")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GpuController {

    private final GpuRepository gpuRepository;

    @GetMapping
    public List<Gpu> listar() {
        return gpuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gpu> obtener(@PathVariable UUID id) {
        return gpuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
