package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {

    List<Comentario> findByProductoIdAndParentIdIsNullOrderByCreatedAtDesc(UUID productoId);

    List<Comentario> findByParentIdOrderByCreatedAtAsc(UUID parentId);

    Optional<Comentario> findByIdAndProductoId(UUID id, UUID productoId);
}
