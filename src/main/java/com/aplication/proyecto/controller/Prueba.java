package com.aplication.proyecto.controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplication.proyecto.entity.Persona;
import com.aplication.proyecto.service.PersonaService;

@Controller
@RequestMapping("persona")
public class Prueba {
	private Log log=LogFactory.getLog(Prueba.class);
	@Autowired
	@Qualifier("personaServiceImp")
	private PersonaService personaService;
	
	@GetMapping("form")
	public String formulario(Model model)
	{	Persona persona=new Persona();		
		model.addAttribute("titulo", "formulario de datos");
		model.addAttribute("persona", persona);
		return "persona/formulario";
	}
	
	@PostMapping("guardar")
	public String guardar(@RequestParam("file") MultipartFile foto, @Valid Persona persona,
			BindingResult result,Model model,RedirectAttributes flash)
	{	
				
		if(!foto.isEmpty()) {//si hay foto ingresa
			log.info("guardar: datos para guardar: "+persona);
				
			
			if(persona.getId()>0)
			{	Persona per2=personaService.findone(persona.getId());
				if(per2.getFoto()!=null){
					log.info("Foto para cambiar: "+per2);
					Path directorioFoto=Paths.get("uploads").resolve(per2.getFoto()).toAbsolutePath();
					File archivo=directorioFoto.toFile();
					log.info("cambiar foto: "+directorioFoto);
					archivo.delete();
					}
			}
			
			Path directorioRecursos=Paths.get("uploads");
			String rootPath=directorioRecursos.toFile().getAbsolutePath();
			log.info("********"+rootPath);
			try {
				byte[] bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				persona.setFoto(foto.getOriginalFilename());
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}	
		
		if(result.hasErrors())
			{  	model.addAttribute("titulo", "formulario de datos");				
				return "persona/formulario";			 
			}
		
		Persona per=personaService.guardar(persona);
		if(per!=null)
			flash.addFlashAttribute("success", "los datos del usuario: "+per.getNombres()+" han sido guardados");
		else
			flash.addAttribute("error", "el usuario no ha sido guardado");
		
		return "redirect:index";				
	}
	
	@GetMapping("index")//lista los datos
	public String admi(Model model)
	{	List<Persona> personas=new ArrayList<Persona>();
		personas=personaService.listar();
		model.addAttribute("personas", personas);//model.addAttribute("titulo", "formulario de datos");
		return "persona/index";
	}
	
	@GetMapping("eliminar/{id}")
	public String eliminar(@PathVariable int id,Model model,RedirectAttributes flash)
	{	
		Persona persona=personaService.findone(id);
		Persona p=personaService.eliminar(id);
		if(p!=null)
			flash.addFlashAttribute("success", "el usuario "+persona.getNombres()+" a sido eliminado");
		else 
			flash.addFlashAttribute("error", "el usuario no pudo ser eliminados");
		log.info("**Archivo foto**"+persona.getFoto());
		if(persona.getFoto()!=null) {
			Path pathFoto=Paths.get("uploads").resolve(persona.getFoto()).toAbsolutePath();
			log.info("eliminar::::"+pathFoto);
			File archivo=pathFoto.toFile();
			if(archivo.exists()&&archivo.canRead())
				if(archivo.delete())
					log.info("**Archivo eliminado**"+persona.getFoto());
		}		
		log.info("eliminar "+id);//model.addAttribute("titulo", "formulario de datos");	
		return "redirect:/persona/index";
	}
	
	@GetMapping("editar/{id}")
	public String editar(@PathVariable int id,Model model)
	{	log.info("editar "+id);
		Persona persona=personaService.editar(id);
		
		if(persona!=null)
		{	model.addAttribute("persona", persona);
			return "persona/formulario";
		}	//model.addAttribute("titulo", "formulario de datos");		
		return "redirect:/persona/index";
	}
	
	@GetMapping("ver/{id}")
	public String ver(@PathVariable int id,Model model)
	{	
		Persona persona=personaService.findone(id);
		model.addAttribute("persona", persona);
		
		Path pathFoto=Paths.get("uploads").resolve(persona.getFoto()).toAbsolutePath();
		log.info("path::::"+pathFoto);
		
		return "persona/ver";
	}	
	/*@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename){
		
		Path pathFoto=Paths.get("uploads").resolve(filename).toAbsolutePath();
		Resource recurso=null;
		log.info(pathFoto);
		try{
			recurso=new UrlResource(pathFoto.toUri());
			if(!recurso.exists() || !recurso.isReadable())			
				throw new RuntimeException("Error: no se puede cargar la imagen: "+pathFoto.toString());			
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}*/
	
}
