package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Gpu;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GpuDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private String vram;
    private Integer potencia;
    private String modelGlb;
    private String modelMesh;
    private String modelColor;

    public static GpuDTO from(Gpu g) {
        var p = g.getProducto();
        return new GpuDTO(
                g.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                g.getVram(),
                g.getPotencia(),
                g.getModelGlb(),
                g.getModelMesh(),
                g.getModelColor()
        );
    }
}
