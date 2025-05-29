package com.bancodedados.gestaoespaco.repository;

import com.bancodedados.gestaoespaco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}