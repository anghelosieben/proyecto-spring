package com.aplication.proyecto.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplication.proyecto.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{
	public Usuario findByUsername(String userid);
}
