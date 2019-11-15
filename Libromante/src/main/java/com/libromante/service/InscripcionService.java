package com.libromante.service;

import java.util.List;

import com.libromante.entity.InscripcionEvento;

public interface InscripcionService {
	
	public abstract List<InscripcionEvento> listAllInscripciones();
	public abstract boolean addInscripcion(InscripcionEvento inscripcion);
	public abstract boolean removeInscripcion(int id);
	public abstract InscripcionEvento findInscripcionById(int id);
	public abstract List<InscripcionEvento> listByEvento();
	public abstract List<InscripcionEvento> findAllUsuarios();
	
}
