package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CaseRepository extends JpaRepository<Case, UUID> {}
