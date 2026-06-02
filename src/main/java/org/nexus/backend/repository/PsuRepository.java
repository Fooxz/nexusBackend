package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Psu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PsuRepository extends JpaRepository<Psu, UUID> {}
