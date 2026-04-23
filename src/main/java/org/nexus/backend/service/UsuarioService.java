package org.nexus.backend.service;

import lombok.RequiredArgsConstructor;
import org.nexus.backend.dto.AuthResponse;
import org.nexus.backend.dto.LoginRequest;
import org.nexus.backend.dto.RegisterRequest;
import org.nexus.backend.model.entity.Usuario;
import org.nexus.backend.repository.UsuarioRepository;
import org.nexus.backend.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol("ROLE_USER");

        usuarioRepository.save(usuario);

        String token = jwtProvider.generateToken(usuario.getEmail(), usuario.getRol());
        return new AuthResponse(token, usuario.getNombre(), usuario.getEmail(), usuario.getRol());
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtProvider.generateToken(usuario.getEmail(), usuario.getRol());
        return new AuthResponse(token, usuario.getNombre(), usuario.getEmail(), usuario.getRol());
    }
}