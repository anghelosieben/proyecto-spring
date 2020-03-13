package com.aplication.proyecto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import net.bytebuddy.implementation.bind.annotation.Empty;



@Entity
@Table(name="personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",unique=true)
	private int id;
	

	@Column(name="nombres")
	@NotEmpty
	private String nombres;
	
	@Column(name="apellidos")
	@NotEmpty
	private String apellidos;
	
	@Column(name="ci")//,unique=true)
	@NotNull(message="El ci es obligatorio")
	private int ci;
	
	@Column(name="email")
	@Email
	@NotEmpty
	private String email;
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	@Column(name="foto")
	private String foto;

	public Persona() {
		
	}
	
	public Persona(int id, String nombres, String apellidos, int ci, String email) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.ci = ci;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public int getCi() {
		return ci;
	}


	public void setCi(int ci) {
		this.ci = ci;
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ci=" + ci + ", email="
				+ email + ", foto=" + foto + "]";
	}
	
}
