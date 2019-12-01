package com.libromante.service;

import java.util.List;

import com.libromante.entity.Reconocimiento;

public interface ReconocimientoService {
	
	public abstract boolean addReconocimiento(Reconocimiento reconocimiento);
	public abstract Reconocimiento findByIdReconocimiento(int id);
	public abstract boolean removeReconocimiento(int id);
	public List<Reconocimiento> findAllReconocimientos();

}
