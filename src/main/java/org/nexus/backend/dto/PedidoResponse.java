package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PedidoResponse {
    private UUID pedidoId;
    private BigDecimal total;
    private String estado;
    private OffsetDateTime createdAt;
}
