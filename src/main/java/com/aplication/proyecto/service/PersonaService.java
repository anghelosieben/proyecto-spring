package com.aplication.proyecto.service;

import java.util.List;

import com.aplication.proyecto.entity.Persona;

public interface PersonaService {
	public abstract List<Persona> listar();
	abstract Persona guardar(Persona persona);
	abstract Persona eliminar(int id);
	abstract Persona editar(int id);
	abstract Persona findone(int id);
}
