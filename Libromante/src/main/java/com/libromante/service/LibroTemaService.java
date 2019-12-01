package com.libromante.service;

import java.util.List;

import com.libromante.entity.LibroTema;

public interface LibroTemaService {
	
	public abstract boolean addLibroTema(LibroTema libroTema);
	public abstract LibroTema findByIdLibroTema(int id);
	public abstract boolean deleteLibroTema(int id);
	public List<LibroTema> findAllLibroTemas();
}
