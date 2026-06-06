package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Case;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CaseDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private String formato;
    private Boolean ventanas;
    private String caseConfigId;
    private String modelGlb;
    private String modelColor;

    public static CaseDTO from(Case c) {
        var p = c.getProducto();
        return new CaseDTO(
                c.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                c.getFormato(),
                c.getVentanas(),
                c.getCaseConfigId(),
                c.getModelGlb(),
                c.getModelColor()
        );
    }
}
