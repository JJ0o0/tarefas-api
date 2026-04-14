package com.j0o0ll.tarefas_api.service;

import com.j0o0ll.tarefas_api.dto.LoginRequest;
import com.j0o0ll.tarefas_api.dto.LoginResponse;
import com.j0o0ll.tarefas_api.dto.RegisterRequest;
import com.j0o0ll.tarefas_api.model.Usuario;
import com.j0o0ll.tarefas_api.repository.UsuarioRepository;
import com.j0o0ll.tarefas_api.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UsuarioService(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public Usuario registrar(RegisterRequest registerRequest) {
        Usuario usuario = new Usuario(
            registerRequest.getNome(),
            registerRequest.getEmail(),
            passwordEncoder.encode(registerRequest.getSenha())
        );

        return repository.save(usuario);
    }

    public LoginResponse autenticar(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha())
        );

        String token = jwtService.gerarToken(loginRequest.getEmail());
        return new LoginResponse(token);
    }
}
