package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Usuario;

@Repository("usuariorepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{
	
	public abstract Usuario findByNombre(String nombre);
	public abstract Usuario findByUsername(String username);
	public abstract Usuario findById(int id);
	public abstract List<Usuario> findAll();
	

}
