package org.nexus.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.ComentarioResponse;
import org.nexus.backend.dto.CrearComentarioRequest;
import org.nexus.backend.dto.RespuestaResponse;
import org.nexus.backend.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos/{productId}/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioResponse>> listar(@PathVariable UUID productId) {
        try {
            return ResponseEntity.ok(comentarioService.listar(productId));
        } catch (RuntimeException e) {
            if ("Producto no encontrado".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            }
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ComentarioResponse> crearComentario(
            @PathVariable UUID productId,
            @RequestBody CrearComentarioRequest request,
            @AuthenticationPrincipal String email) {

        ComentarioResponse created = comentarioService.crearComentario(productId, email, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{commentId}/respuestas")
    public ResponseEntity<RespuestaResponse> crearRespuesta(
            @PathVariable UUID productId,
            @PathVariable UUID commentId,
            @RequestBody CrearComentarioRequest request,
            @AuthenticationPrincipal String email) {

        try {
            RespuestaResponse created = comentarioService.crearRespuesta(
                    productId, commentId, email, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            if ("Comentario no encontrado".equals(e.getMessage())) {
                return ResponseEntity.notFound().build();
            }
            throw e;
        }
    }
}
