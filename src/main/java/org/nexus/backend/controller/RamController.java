package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.RamDTO;
import org.nexus.backend.repository.RamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rams")
@RequiredArgsConstructor
public class RamController {

    private final RamRepository ramRepository;

    @GetMapping
    public List<RamDTO> listar() {
        return ramRepository.findAll().stream()
                .map(RamDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RamDTO> obtener(@PathVariable UUID id) {
        return ramRepository.findById(id)
                .map(RamDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
