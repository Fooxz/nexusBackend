package org.nexus.backend.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class AdminProductoRequest {

    private static final Set<String> COMMON_FIELDS = Set.of(
            "id", "nombre", "marca", "categoria", "precio", "precioNormal", "descuento",
            "imagen", "descripcion", "stock", "activo", "specs"
    );

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

    @JsonIgnore
    private final Map<String, Object> flattenedSpecs = new HashMap<>();

    @JsonAnySetter
    public void setFlattenedSpec(String key, Object value) {
        if (!COMMON_FIELDS.contains(key)) {
            flattenedSpecs.put(key, value);
        }
    }

    public Map<String, Object> resolvedSpecs() {
        Map<String, Object> merged = new HashMap<>();
        if (specs != null) {
            merged.putAll(specs);
        }
        merged.putAll(flattenedSpecs);
        return merged;
    }
}
