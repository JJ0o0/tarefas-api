package com.j0o0ll.tarefas_api.service;

import com.j0o0ll.tarefas_api.dto.TarefaRequest;
import com.j0o0ll.tarefas_api.dto.TarefaResponse;
import com.j0o0ll.tarefas_api.dto.TarefaUpdateRequest;
import com.j0o0ll.tarefas_api.model.Tarefa;
import com.j0o0ll.tarefas_api.model.Usuario;
import com.j0o0ll.tarefas_api.repository.TarefaRepository;
import com.j0o0ll.tarefas_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository repository;
    private final UsuarioRepository usuarioRepository;

    public TarefaService(TarefaRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public TarefaResponse criar(TarefaRequest request, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefa tarefa = new Tarefa(
                request.getTitulo(),
                request.getDescricao(),
                false,
                usuario
        );

        return new TarefaResponse(repository.save(tarefa));
    }

    public List<TarefaResponse> listarPorUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return repository.findByUsuario(usuario).stream()
                .map(TarefaResponse::new)
                .toList();
    }

    public Optional<TarefaResponse> atualizar(Long id, TarefaUpdateRequest dados, String email) {
        return repository.findById(id)
                .filter(t -> t.getUsuario().getEmail().equals(email))
                .map(tarefa -> {
                   tarefa.setTitulo(dados.getTitulo());
                   tarefa.setDescricao(dados.getDescricao());
                   tarefa.setConcluida(dados.isConcluida());

                   return new TarefaResponse(repository.save(tarefa));
                });
    }

    public boolean deletar(Long id, String email) {
        return repository.findById(id)
                .filter(t -> t.getUsuario().getEmail().equals(email))
                .map(tarefa -> {
                    repository.delete(tarefa);
                    return true;
                }).orElse(false);
    }
}
