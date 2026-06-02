package org.nexus.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "celulares")
public class Celular {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String storage;

    @Column(nullable = false)
    private String color;

    @Column(name = "precio_normal")
    private BigDecimal precioNormal;

    private Integer descuento;

    // Specs técnicas
    private String pantalla;
    private String resolucion;
    private String so;
    private String procesador;
    private String ram;
    private String almacenamiento;

    @Column(name = "camara_principal")
    private String camaraPrincipal;

    @Column(name = "camara_frontal")
    private String camaraFrontal;

    private String bateria;

    @Column(name = "tiene_nfc")
    private Boolean tieneNfc;

    @Column(name = "tiene_5g")
    private Boolean tiene5g;
}