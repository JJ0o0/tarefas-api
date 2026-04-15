package com.j0o0ll.tarefas_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class TarefaUpdateRequest {
    @NotBlank
    @Schema(example = "Novo Título")
    private String titulo;

    @NotBlank
    @Schema(example = "Nova Descrição")
    private String descricao;

    @NotBlank
    @Schema(example = "false", type = "boolean")
    private boolean concluida;

    public TarefaUpdateRequest() {}
    public TarefaUpdateRequest(String titulo, String descricao, boolean concluida) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
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
