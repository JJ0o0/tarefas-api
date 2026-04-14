package com.j0o0ll.tarefas_api.repository;

import com.j0o0ll.tarefas_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Optional evita valor null
    Optional<Usuario> findByEmail(String email);
}
