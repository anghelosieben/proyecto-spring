package com.aplication.proyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aplication.proyecto.entity.Role;
import com.aplication.proyecto.entity.Usuario;
import com.aplication.proyecto.repository.UsuarioRepository;

@Service("usuarioDetailService")
public class UsuarioDetailService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=usuarioRepository.findByUsername(username);
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		for(Role rol:usuario.getRoles())
			authorities.add(new SimpleGrantedAuthority(rol.getRol()));
		
		return new User(usuario.getUsername(), usuario.getPassword(),usuario.isEnabled(), true, true, true, authorities);
	}

}
