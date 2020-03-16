package com.libromante.service;

import java.util.List;

import com.libromante.entity.Genero;

public interface GeneroService {
	
	public abstract boolean addGenero(Genero genero);
	public abstract Genero findById(int id);
	public abstract boolean removeGenero(int id);
	public List<Genero> findAllGeneros();
	public List<Genero> findByIdGenero(int id);

}
