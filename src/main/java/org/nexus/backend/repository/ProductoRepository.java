package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByActivoTrue();
    List<Producto> findByCategoriaAndActivoTrue(String categoria);
}