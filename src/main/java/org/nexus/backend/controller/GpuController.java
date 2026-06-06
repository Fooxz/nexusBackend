package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.GpuDTO;
import org.nexus.backend.repository.GpuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gpus")
@RequiredArgsConstructor
public class GpuController {

    private final GpuRepository gpuRepository;

    @GetMapping
    public List<GpuDTO> listar() {
        return gpuRepository.findAll().stream()
                .map(GpuDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GpuDTO> obtener(@PathVariable UUID id) {
        return gpuRepository.findById(id)
                .map(GpuDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
