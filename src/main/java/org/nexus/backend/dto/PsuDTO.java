package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Psu;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PsuDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private Integer potencia;
    private String certificacion;
    private Boolean modular;
    private String modelGlb;
    private String modelMesh;
    private String modelColor;

    public static PsuDTO from(Psu psu) {
        var p = psu.getProducto();
        return new PsuDTO(
                psu.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                psu.getPotencia(),
                psu.getCertificacion(),
                psu.getModular(),
                psu.getModelGlb(),
                psu.getModelMesh(),
                psu.getModelColor()
        );
    }
}
