package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Storage;
import org.nexus.backend.repository.StorageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/storages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StorageController {

    private final StorageRepository storageRepository;

    @GetMapping
    public List<Storage> listar() {
        return storageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Storage> obtener(@PathVariable UUID id) {
        return storageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
