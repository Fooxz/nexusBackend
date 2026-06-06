package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ComentarioAuthorResponse {
    private UUID id;
    private String nombre;
}
