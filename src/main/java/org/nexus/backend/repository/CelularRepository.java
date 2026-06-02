package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Celular;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CelularRepository extends JpaRepository<Celular, UUID> {}
