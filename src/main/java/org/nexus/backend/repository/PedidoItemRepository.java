package org.nexus.backend.repository;

import org.nexus.backend.model.entity.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, UUID> {
    List<PedidoItem> findByPedidoId(UUID pedidoId);
}
