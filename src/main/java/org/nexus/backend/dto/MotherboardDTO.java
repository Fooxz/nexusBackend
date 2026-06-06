package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Motherboard;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MotherboardDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private String socket;
    private String chipset;
    private String formatoRam;
    private Integer slotsRam;
    private String modelGlb;
    private String modelMesh;
    private String modelColor;

    public static MotherboardDTO from(Motherboard m) {
        var p = m.getProducto();
        return new MotherboardDTO(
                m.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                m.getSocket(),
                m.getChipset(),
                m.getFormatoRam(),
                m.getSlotsRam(),
                m.getModelGlb(),
                m.getModelMesh(),
                m.getModelColor()
        );
    }
}
