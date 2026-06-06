package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Cpu;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CpuDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private String socket;
    private Integer nucleos;
    private Integer hilos;
    private String velocidad;
    private Integer tdp;
    private String modelGlb;
    private String modelMesh;
    private String modelColor;

    public static CpuDTO from(Cpu c) {
        var p = c.getProducto();
        return new CpuDTO(
                c.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                c.getSocket(),
                c.getNucleos(),
                c.getHilos(),
                c.getVelocidad(),
                c.getTdp(),
                c.getModelGlb(),
                c.getModelMesh(),
                c.getModelColor()
        );
    }
}
