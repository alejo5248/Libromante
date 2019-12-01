package com.libromante.service;

import java.util.List;

import com.libromante.entity.Pais;

public interface PaisService {
	public abstract boolean addPais(Pais pais);
	public abstract Pais findByIdPais(int id);
	public abstract boolean removePais(int id);
	public List<Pais> findAllPaises();
	

}
