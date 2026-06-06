package org.nexus.backend.service;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.CrearPedidoRequest;
import org.nexus.backend.dto.PedidoResponse;
import org.nexus.backend.model.entity.Pedido;
import org.nexus.backend.model.entity.PedidoItem;
import org.nexus.backend.model.entity.Usuario;
import org.nexus.backend.repository.PedidoItemRepository;
import org.nexus.backend.repository.PedidoRepository;
import org.nexus.backend.repository.ProductoRepository;
import org.nexus.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoItemRepository pedidoItemRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoResponse crearPedido(String emailUsuario, CrearPedidoRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        BigDecimal total = request.getItems().stream()
                .map(i -> i.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(i.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuario.getId());
        pedido.setNombreComprador(request.getNombreComprador());
        pedido.setTotal(total);
        pedido.setEstado("CONFIRMADO");
        Pedido saved = pedidoRepository.save(pedido);

        request.getItems().forEach(i -> {
            PedidoItem item = new PedidoItem();
            item.setPedidoId(saved.getId());
            item.setProductoId(i.getProductoId());
            item.setCantidad(i.getCantidad());
            item.setPrecioUnitario(i.getPrecioUnitario());
            pedidoItemRepository.save(item);

            productoRepository.findById(i.getProductoId()).ifPresent(producto -> {
                int nuevoStock = Math.max(0, producto.getStock() - i.getCantidad());
                producto.setStock(nuevoStock);
                if (nuevoStock == 0) producto.setActivo(false);
                productoRepository.save(producto);
            });
        });

        return new PedidoResponse(
                saved.getId(), saved.getTotal(),
                saved.getEstado(), saved.getCreatedAt()
        );
    }
}
