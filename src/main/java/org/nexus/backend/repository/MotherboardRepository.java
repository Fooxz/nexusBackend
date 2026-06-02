package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MotherboardRepository extends JpaRepository<Motherboard, UUID> {}
