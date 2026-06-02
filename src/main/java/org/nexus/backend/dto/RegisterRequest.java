package org.nexus.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor  // ← Agregar esta línea
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String nombre;
    private String email;
    private String password;
}
