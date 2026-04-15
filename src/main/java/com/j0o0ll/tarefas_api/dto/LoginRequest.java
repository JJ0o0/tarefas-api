package com.j0o0ll.tarefas_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    @Schema(example = "fulano.silva17@email.com")
    private String email;

    @NotBlank
    @Schema(example = "senhasecreta1234")
    private String senha;

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
