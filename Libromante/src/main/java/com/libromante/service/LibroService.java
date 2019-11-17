package com.libromante.service;

import java.util.List;

import com.libromante.entity.Libro;

public interface LibroService {
	
	public List<Libro> findByNombre(String nombre);

	public abstract Libro findById(int id);
	public abstract boolean removeLibro(int id);
	public abstract boolean addLibro(Libro libro);
	public List<Libro> findAllLibros();
}
