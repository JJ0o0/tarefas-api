package com.j0o0ll.tarefas_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank
    @Schema(example = "Fulano da Silva")
    private String nome;

    @NotBlank
    @Schema(example = "fulano.silva17@email.com")
    private String email;

    @NotBlank
    @Schema(example = "senhasecreta1234")
    private String senha;

    public RegisterRequest(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
