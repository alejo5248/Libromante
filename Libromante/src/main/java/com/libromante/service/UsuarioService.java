package com.libromante.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.libromante.entity.Usuario;


public interface UsuarioService extends UserDetailsService {
	public abstract List<Usuario> listAllUsuarios();
	public abstract boolean addUsuario(Usuario usuario);
	public abstract boolean updateUsuario(Usuario usuario);
	public abstract boolean removeUsuario(int id);
	public abstract Usuario findUsuarioById(int id);
	public abstract Usuario findByUsername(String username);
	
	

}
