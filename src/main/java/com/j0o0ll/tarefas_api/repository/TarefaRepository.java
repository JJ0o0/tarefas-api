package com.j0o0ll.tarefas_api.repository;

import com.j0o0ll.tarefas_api.model.Tarefa;
import com.j0o0ll.tarefas_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuario(Usuario usuario);
}
