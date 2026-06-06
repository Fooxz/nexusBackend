package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Cooling;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CoolingDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private String tipo;
    private Integer tdpSoporte;
    private String modelGlb;
    private String modelMesh;
    private String modelColor;

    public static CoolingDTO from(Cooling c) {
        var p = c.getProducto();
        return new CoolingDTO(
                c.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                c.getTipo(),
                c.getTdpSoporte(),
                c.getModelGlb(),
                c.getModelMesh(),
                c.getModelColor()
        );
    }
}
