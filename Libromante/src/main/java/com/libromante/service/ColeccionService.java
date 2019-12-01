package com.libromante.service;

import java.util.List;

import com.libromante.entity.Coleccion;

public interface ColeccionService {
	
	public abstract boolean addColeccion(Coleccion coleccion);
	public abstract Coleccion findByIdColeccion(int id);
	public abstract boolean removeColeccion(int id);
	public List<Coleccion> findAllColecciones();

}
