package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class AdminStatsResponse {
    private long totalProductos;
    private long totalPedidos;
    private long totalUsuarios;
    private BigDecimal ingresos;
    private List<CategoriaCount> categorias;

    @Data
    @AllArgsConstructor
    public static class CategoriaCount {
        private String nombre;
        private long total;
    }
}
