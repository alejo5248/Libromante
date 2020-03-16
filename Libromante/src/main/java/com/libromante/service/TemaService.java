package com.libromante.service;

import java.util.List;

import com.libromante.entity.Tema;

public interface TemaService {
	
	public abstract boolean addTema(Tema tema);
	public abstract Tema findByIdTema(int id);
	public abstract boolean removeTema(int id);
	public List<Tema> findAllTemas();
	public List<Tema> findById(int id);

}
