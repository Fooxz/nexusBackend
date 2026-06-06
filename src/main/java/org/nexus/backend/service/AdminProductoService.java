package org.nexus.backend.service;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.AdminProductoRequest;
import org.nexus.backend.dto.AdminProductoResponse;
import org.nexus.backend.model.entity.Producto;
import org.nexus.backend.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductoService {

    private static final Set<String> CATEGORIAS_VALIDAS = Set.of(
            "cpu", "gpu", "ram", "motherboard", "storage", "psu", "case", "cooling",
            "celular", "laptop", "tablet", "monitor", "periferico", "audio", "redes",
            "consola", "smartwatch", "camara", "drone"
    );

    private final ProductoRepository productoRepository;

    public List<AdminProductoResponse> listar() {
        return productoRepository.findAll().stream()
                .map(AdminProductoResponse::from)
                .collect(Collectors.toList());
    }

    public AdminProductoResponse crear(AdminProductoRequest request) {
        validarRequest(request);
        Producto producto = applyRequest(new Producto(), request);
        return AdminProductoResponse.from(productoRepository.save(producto));
    }

    public AdminProductoResponse actualizar(UUID id, AdminProductoRequest request) {
        validarRequest(request);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        applyRequest(producto, request);
        return AdminProductoResponse.from(productoRepository.save(producto));
    }

    public void eliminar(UUID id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    private void validarRequest(AdminProductoRequest request) {
        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (request.getMarca() == null || request.getMarca().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La marca es obligatoria");
        }
        if (request.getCategoria() == null || request.getCategoria().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoría es obligatoria");
        }
        if (request.getPrecio() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio es obligatorio");
        }
        validarCategoria(request.getCategoria());
    }

    private void validarCategoria(String categoria) {
        if (!CATEGORIAS_VALIDAS.contains(categoria.toLowerCase())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Categoría no válida: " + categoria
            );
        }
    }

    private Producto applyRequest(Producto producto, AdminProductoRequest request) {
        producto.setNombre(request.getNombre());
        producto.setMarca(request.getMarca());
        producto.setCategoria(request.getCategoria().toLowerCase());
        producto.setPrecio(request.getPrecio());
        producto.setPrecioNormal(request.getPrecioNormal());
        producto.setDescuento(request.getDescuento() != null ? request.getDescuento() : 0);
        producto.setImagen(request.getImagen());
        producto.setDescripcion(request.getDescripcion());
        producto.setStock(request.getStock() != null ? request.getStock() : 0);
        producto.setActivo(request.getActivo() != null ? request.getActivo() : true);

        Map<String, Object> specs = request.resolvedSpecs();
        producto.setSpecs(specs.isEmpty() ? null : specs);

        return producto;
    }
}
