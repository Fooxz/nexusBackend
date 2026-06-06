package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.StorageDTO;
import org.nexus.backend.repository.StorageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/storages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StorageController {

    private final StorageRepository storageRepository;

    @GetMapping
    public List<StorageDTO> listar() {
        return storageRepository.findAll().stream()
                .map(StorageDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageDTO> obtener(@PathVariable UUID id) {
        return storageRepository.findById(id)
                .map(StorageDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
