package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<Storage, UUID> {}
