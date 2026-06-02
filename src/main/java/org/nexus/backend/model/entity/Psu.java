package org.nexus.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "psus")
public class Psu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer potencia;

    private String certificacion;

    private Boolean modular;

    @Column(name = "model_glb")
    private String modelGlb;

    @Column(name = "model_mesh")
    private String modelMesh;

    @Column(name = "model_color")
    private String modelColor;
}
