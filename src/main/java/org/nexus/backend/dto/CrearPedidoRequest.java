package org.nexus.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CrearPedidoRequest {
    private String nombreComprador;
    private List<PedidoItemRequest> items;
}
