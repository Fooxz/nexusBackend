package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    long countByEstado(String estado);
}
