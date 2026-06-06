package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nexus.backend.model.entity.Producto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AdminProductoResponse {

    private UUID id;
    private String nombre;
    private String marca;
    private String categoria;
    private BigDecimal precio;
    private BigDecimal precioNormal;
    private Integer descuento;
    private String imagen;
    private String descripcion;
    private Integer stock;
    private Boolean activo;
    private Map<String, Object> specs;

    public static AdminProductoResponse from(Producto producto) {
        Map<String, Object> specs = producto.getSpecs() != null
                ? producto.getSpecs()
                : Collections.emptyMap();

        return new AdminProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getMarca(),
                producto.getCategoria(),
                producto.getPrecio(),
                producto.getPrecioNormal(),
                producto.getDescuento(),
                producto.getImagen(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getActivo(),
                specs
        );
    }
}
