package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Storage;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class StorageDTO {
    private UUID id;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private String imagen;
    private String categoria;
    private Integer stock;
    private String capacidad;
    private String tipo;
    private String velocidadLec;
    private String modelGlb;
    private String modelMesh;
    private String modelColor;

    public static StorageDTO from(Storage s) {
        var p = s.getProducto();
        return new StorageDTO(
                s.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getMarca(),
                p.getImagen(),
                p.getCategoria(),
                p.getStock(),
                s.getCapacidad(),
                s.getTipo(),
                s.getVelocidadLec(),
                s.getModelGlb(),
                s.getModelMesh(),
                s.getModelColor()
        );
    }
}
