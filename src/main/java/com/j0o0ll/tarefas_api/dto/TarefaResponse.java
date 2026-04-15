package com.j0o0ll.tarefas_api.dto;

import com.j0o0ll.tarefas_api.model.Tarefa;

public class TarefaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;

    public TarefaResponse(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.concluida = tarefa.isConcluida();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }
}
