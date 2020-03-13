package com.aplication.proyecto.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonaModel {
	
	public class Persona {
		
		private int id;
		@NotEmpty
		@Size(min = 5,max=20)
		private String nombres;
		@NotEmpty
		private String apellidos;
		@NotNull
		private int ci;
		
		public Persona() {
			
		}
		
		public Persona(int id, String nombres, String apellidos, int ci) {
			//super();
			this.id = id;
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.ci = ci;
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
			return "Persona [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ci=" + ci + "]";
		}
		
	}
}
