package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.AdminProductoRequest;
import org.nexus.backend.dto.AdminProductoResponse;
import org.nexus.backend.dto.AdminStatsResponse;
import org.nexus.backend.dto.UsuarioAdminResponse;
import org.nexus.backend.model.entity.Pedido;
import org.nexus.backend.repository.PedidoRepository;
import org.nexus.backend.repository.ProductoRepository;
import org.nexus.backend.repository.UsuarioRepository;
import org.nexus.backend.service.AdminProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AdminProductoService adminProductoService;

    @GetMapping("/stats")
    public ResponseEntity<AdminStatsResponse> getStats() {
        long totalProductos = productoRepository.count();
        long totalPedidos = pedidoRepository.count();
        long totalUsuarios = usuarioRepository.count();

        BigDecimal ingresos = pedidoRepository.findAll()
                .stream()
                .map(p -> p.getTotal() != null ? p.getTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Long> catMap = new HashMap<>();
        productoRepository.findAll().forEach(p ->
                catMap.merge(p.getCategoria(), 1L, Long::sum)
        );
        List<AdminStatsResponse.CategoriaCount> categorias = catMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(e -> new AdminStatsResponse.CategoriaCount(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AdminStatsResponse(
                totalProductos, totalPedidos, totalUsuarios, ingresos, categorias
        ));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioAdminResponse>> getUsuarios() {
        List<UsuarioAdminResponse> lista = usuarioRepository.findAll()
                .stream()
                .map(u -> new UsuarioAdminResponse(u.getId(), u.getNombre(), u.getEmail(), u.getRol()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getPedidos() {
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable UUID id) {
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/productos")
    public ResponseEntity<AdminProductoResponse> crearProducto(@RequestBody AdminProductoRequest request) {
        return ResponseEntity.ok(adminProductoService.crear(request));
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<AdminProductoResponse> actualizarProducto(
            @PathVariable UUID id, @RequestBody AdminProductoRequest request) {
        return ResponseEntity.ok(adminProductoService.actualizar(id, request));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable UUID id) {
        adminProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productos")
    public ResponseEntity<List<AdminProductoResponse>> getProductos() {
        return ResponseEntity.ok(adminProductoService.listar());
    }

}
