package org.nexus.backend.service;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.*;
import org.nexus.backend.model.entity.Comentario;
import org.nexus.backend.model.entity.Usuario;
import org.nexus.backend.repository.ComentarioRepository;
import org.nexus.backend.repository.ProductoRepository;
import org.nexus.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<ComentarioResponse> listar(UUID productoId) {
        validarProducto(productoId);

        return comentarioRepository
                .findByProductoIdAndParentIdIsNullOrderByCreatedAtDesc(productoId)
                .stream()
                .map(this::toComentarioResponse)
                .collect(Collectors.toList());
    }

    public ComentarioResponse crearComentario(UUID productoId, String email, CrearComentarioRequest request) {
        validarProducto(productoId);
        validarContenido(request.getContent());
        Usuario usuario = obtenerUsuario(email);

        Comentario comentario = new Comentario();
        comentario.setProductoId(productoId);
        comentario.setUsuarioId(usuario.getId());
        comentario.setContent(request.getContent().trim());

        Comentario saved = comentarioRepository.save(comentario);
        return toComentarioResponse(saved);
    }

    public RespuestaResponse crearRespuesta(
            UUID productoId, UUID commentId, String email, CrearComentarioRequest request) {

        validarProducto(productoId);
        validarContenido(request.getContent());

        Comentario parent = comentarioRepository.findByIdAndProductoId(commentId, productoId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        if (parent.getParentId() != null) {
            throw new RuntimeException("Solo se puede responder a comentarios principales");
        }

        Usuario usuario = obtenerUsuario(email);

        Comentario respuesta = new Comentario();
        respuesta.setProductoId(productoId);
        respuesta.setUsuarioId(usuario.getId());
        respuesta.setContent(request.getContent().trim());
        respuesta.setParentId(parent.getId());

        Comentario saved = comentarioRepository.save(respuesta);
        return toRespuestaResponse(saved);
    }

    private ComentarioResponse toComentarioResponse(Comentario comentario) {
        List<RespuestaResponse> replies = comentarioRepository
                .findByParentIdOrderByCreatedAtAsc(comentario.getId())
                .stream()
                .map(this::toRespuestaResponse)
                .collect(Collectors.toList());

        return new ComentarioResponse(
                comentario.getId(),
                comentario.getContent(),
                comentario.getCreatedAt(),
                toAuthor(comentario.getUsuarioId()),
                replies
        );
    }

    private RespuestaResponse toRespuestaResponse(Comentario respuesta) {
        return new RespuestaResponse(
                respuesta.getId(),
                respuesta.getContent(),
                respuesta.getCreatedAt(),
                toAuthor(respuesta.getUsuarioId())
        );
    }

    private ComentarioAuthorResponse toAuthor(UUID usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .map(u -> new ComentarioAuthorResponse(u.getId(), u.getNombre()))
                .orElse(new ComentarioAuthorResponse(usuarioId, "Usuario"));
    }

    private void validarProducto(UUID productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    private void validarContenido(String content) {
        if (content == null || content.isBlank()) {
            throw new RuntimeException("El contenido no puede estar vacío");
        }
    }

    private Usuario obtenerUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
