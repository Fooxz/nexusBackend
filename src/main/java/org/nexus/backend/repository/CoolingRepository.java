package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Cooling;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CoolingRepository extends JpaRepository<Cooling, UUID> {}
