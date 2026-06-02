package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.model.entity.Cooling;
import org.nexus.backend.repository.CoolingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/coolings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CoolingController {

    private final CoolingRepository coolingRepository;

    @GetMapping
    public List<Cooling> listar() {
        return coolingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cooling> obtener(@PathVariable UUID id) {
        return coolingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
