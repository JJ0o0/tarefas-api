package com.j0o0ll.tarefas_api.dto;

public class RegisterRequest {
    private String nome;
    private String email;
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
