package com.libromante.service;

import java.util.List;

import com.libromante.entity.Autor;

public interface AutorService {
	
	public abstract boolean removeAutor(int id);
	public abstract boolean addAutor(Autor autor);
	public abstract Autor findByIdAutor(int id);
	public List<Autor> findAllAutores();
	public List<Autor> findByNombreAutor(String nombre);
	
	

}
