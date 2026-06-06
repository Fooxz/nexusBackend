package org.nexus.backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PedidoItemRequest {
    private UUID productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
