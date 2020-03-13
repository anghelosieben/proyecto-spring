package com.aplication.proyecto.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@Column(name="userid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true,length = 45)
	private String username;
	@Column(length = 60)
	private String password;
	
	private boolean enabled;
	
	@JoinColumn(name="userid")
	@OneToMany(fetch =FetchType.EAGER,cascade = CascadeType.ALL )
	private List<Role> roles;

	public Usuario(long id, String username, String password, boolean enabled, List<Role> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	public Usuario() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", roles=" + roles + "]";
	}
		
}
