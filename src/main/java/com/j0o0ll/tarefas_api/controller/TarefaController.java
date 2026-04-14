package com.j0o0ll.tarefas_api.controller;

import com.j0o0ll.tarefas_api.model.Tarefa;
import com.j0o0ll.tarefas_api.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        String email = getUserEmail();

        return ResponseEntity
                .status(200)
                .body(service.listarPorUsuario(email));
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        String email = getUserEmail();

        return ResponseEntity
                .status(201)
                .body(service.criar(tarefa, email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return service.atualizar(id, tarefa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
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
