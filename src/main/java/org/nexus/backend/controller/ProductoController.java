package org.nexus.backend.controller;

import org.nexus.backend.model.entity.Producto;
import org.nexus.backend.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<Producto> listar(@RequestParam(required = false) String categoria) {
        if (categoria != null && !categoria.isEmpty()) {
            return productoRepository.findByCategoriaAndActivoTrue(categoria);
        }
        return productoRepository.findByActivoTrue();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Producto> porCategoria(@PathVariable String categoria) {
        return productoRepository.findByCategoriaAndActivoTrue(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable UUID id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
