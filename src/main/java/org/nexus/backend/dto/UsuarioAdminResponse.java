package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UsuarioAdminResponse {
    private UUID id;
    private String nombre;
    private String email;
    private String rol;
}
