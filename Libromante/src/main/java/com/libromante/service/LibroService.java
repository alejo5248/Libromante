package com.libromante.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.libromante.entity.Libro;

public interface LibroService {
	
	public List<Libro> findByNombre(String nombre);

	public abstract Libro findById(int id);
	public abstract boolean removeLibro(int id);
	public abstract boolean addLibro(Libro libro);
	public List<Libro> findAllLibros();
	public List<Libro> findNew();
	public List<Libro> findAllPortadas();
	public abstract Page<Libro> listPorPaginacion(Pageable pageable);
	public List<Libro> findAll();
	public List<Libro> findAllById(int id);
	public List<Libro> findAllPromocion();
}
