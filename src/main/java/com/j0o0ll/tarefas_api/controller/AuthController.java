package com.j0o0ll.tarefas_api.controller;

import com.j0o0ll.tarefas_api.dto.LoginRequest;
import com.j0o0ll.tarefas_api.dto.LoginResponse;
import com.j0o0ll.tarefas_api.dto.RegisterRequest;
import com.j0o0ll.tarefas_api.model.Usuario;
import com.j0o0ll.tarefas_api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity
                .status(200)
                .body(service.registrar(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> autenticar(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .status(200)
                .body(service.autenticar(loginRequest));
    }
}
