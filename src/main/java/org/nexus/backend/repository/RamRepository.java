package org.nexus.backend.repository;

import org.nexus.backend.model.entity.Ram;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RamRepository extends JpaRepository<Ram, UUID> {

    @EntityGraph(attributePaths = {"producto"})
    List<Ram> findAll();
}
