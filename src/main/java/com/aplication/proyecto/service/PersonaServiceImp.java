package com.aplication.proyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aplication.proyecto.entity.Persona;
import com.aplication.proyecto.repository.PersonaRepository;

@Service("personaServiceImp")
public class PersonaServiceImp implements PersonaService{
	private Log log=LogFactory.getLog(PersonaServiceImp.class);
	
	@Autowired
	@Qualifier("personaRepository")
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> listar() {
		List<Persona> personas=new ArrayList<Persona>();
		personas=personaRepository.findAll();			
		return personas;
	}

	@Override
	public Persona guardar(Persona persona) {	
				
		if(persona!=null)
		{	personaRepository.save(persona);
			log.info("persona guardada guardar "+persona);
		}
		return persona;
	}

	@Override
	public Persona eliminar(int id) {
		Persona persona=personaRepository.findById(id);
		if(persona!=null)
			{personaRepository.delete(persona);
			 log.info(""+persona);
			}
		else 
			log.info("id no existe"+id);		
		return persona;
	}

	@Override
	public Persona editar(int id)
	{
		Persona persona=personaRepository.findById(id);	
		return persona;
	}

	@Override
	public Persona findone(int id) {
		Persona persona=personaRepository.findById(id);	
		return persona;
	}
	
}
