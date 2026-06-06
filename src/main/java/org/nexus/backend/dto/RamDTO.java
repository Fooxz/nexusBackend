package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Ram;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RamDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private String capacidad;
    private String tipo;
    private String velocidad;
    private String modelGlb;
    private String modelMesh;
    private String modelColor;

    public static RamDTO from(Ram r) {
        var p = r.getProducto();
        return new RamDTO(
                r.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                r.getCapacidad(),
                r.getTipo(),
                r.getVelocidad(),
                r.getModelGlb(),
                r.getModelMesh(),
                r.getModelColor()
        );
    }
}
