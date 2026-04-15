package com.j0o0ll.tarefas_api.controller;

import com.j0o0ll.tarefas_api.dto.LoginRequest;
import com.j0o0ll.tarefas_api.dto.LoginResponse;
import com.j0o0ll.tarefas_api.dto.RegisterRequest;
import com.j0o0ll.tarefas_api.model.Usuario;
import com.j0o0ll.tarefas_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação", description = "Gerenciamento de Usuários")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar usuário")
    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity
                .status(200)
                .body(service.registrar(registerRequest));
    }

    @Operation(summary = "Logar usuário e retornar chave de autenticação JWT")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> autenticar(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .status(200)
                .body(service.autenticar(loginRequest));
    }
}
