package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface GpuRepository extends JpaRepository<Gpu, UUID> {}
