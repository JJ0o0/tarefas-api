package com.j0o0ll.tarefas_api.controller;

import com.j0o0ll.tarefas_api.dto.TarefaRequest;
import com.j0o0ll.tarefas_api.dto.TarefaResponse;
import com.j0o0ll.tarefas_api.dto.TarefaUpdateRequest;
import com.j0o0ll.tarefas_api.model.Tarefa;
import com.j0o0ll.tarefas_api.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tarefas", description = "Gerenciamento de tarefas do usuário")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar tarefas do usuário logado")
    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listarTarefas() {
        String email = getUserEmail();

        return ResponseEntity
                .status(200)
                .body(service.listarPorUsuario(email));
    }

    @Operation(summary = "Criar tarefa do usuário")
    @PostMapping
    public ResponseEntity<TarefaResponse> criar(@Valid @RequestBody TarefaRequest request) {
        String email = getUserEmail();

        return ResponseEntity
                .status(201)
                .body(service.criar(request, email));
    }

    @Operation(summary = "Atualizar tarefa do usuário logado baseado no ID")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody TarefaUpdateRequest dados) {
        String email = getUserEmail();

        return service.atualizar(id, dados, email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar tarefa baseado no ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        String email = getUserEmail();

        if (service.deletar(id, email)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private String getUserEmail() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return email;
    }
}
