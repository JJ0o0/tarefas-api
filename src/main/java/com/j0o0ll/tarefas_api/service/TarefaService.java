package com.j0o0ll.tarefas_api.service;

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

    public Tarefa criar(Tarefa tarefa, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        tarefa.setUsuario(usuario);

        return repository.save(tarefa);
    }

    public List<Tarefa> listarPorUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Tarefa> tarefas = repository.findByUsuario(usuario);

        return tarefas;
    }

    public Optional<Tarefa> atualizar(Long id, Tarefa dados) {
        return repository.findById(id)
                .map(tarefa -> {
                   tarefa.setTitulo(dados.getTitulo());
                   tarefa.setDescricao(dados.getDescricao());
                   tarefa.setConcluida(dados.isConcluida());

                   return repository.save(tarefa);
                });
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
