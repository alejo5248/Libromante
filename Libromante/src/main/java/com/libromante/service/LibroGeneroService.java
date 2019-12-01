package com.libromante.service;

import java.util.List;

import com.libromante.entity.LibroGenero;

public interface LibroGeneroService {

	public abstract boolean addLibroGenero(LibroGenero libroGenero);
	public abstract LibroGenero findByIdLibroGenero(int id);
	public abstract boolean removeLibroGenero(int id);
	public List<LibroGenero> findAllLibroGeneros();
}
