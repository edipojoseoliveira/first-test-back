package com.edipo.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edipo.first.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
