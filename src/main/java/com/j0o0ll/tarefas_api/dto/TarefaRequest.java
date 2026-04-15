package com.j0o0ll.tarefas_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class TarefaRequest {
    @NotBlank
    @Schema(example = "Planejar Reunião de Alinhamento")
    private String titulo;

    @NotBlank
    @Schema(example = "Definir pauta, revisar blockers e metas da sprint.")
    private String descricao;

    public TarefaRequest() {}
    public TarefaRequest(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
