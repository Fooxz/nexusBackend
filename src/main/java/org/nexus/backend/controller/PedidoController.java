package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.CrearPedidoRequest;
import org.nexus.backend.dto.PedidoResponse;
import org.nexus.backend.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(
            @RequestBody CrearPedidoRequest request,
            @AuthenticationPrincipal String email) {

        return ResponseEntity.ok(
                pedidoService.crearPedido(email, request)
        );
    }
}
