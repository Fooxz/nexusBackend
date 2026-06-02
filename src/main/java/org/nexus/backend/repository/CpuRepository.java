// CpuRepository.java
package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CpuRepository extends JpaRepository<Cpu, UUID> {}
