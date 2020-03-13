package com.aplication.proyecto.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplication.proyecto.entity.Persona;
@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Serializable>{
	abstract Persona findById(int id); 
	abstract List<Persona> findAll();
	 
}
