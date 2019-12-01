package com.libromante.service;

import java.util.List;

import com.libromante.entity.LibroReconocimiento;

public interface LibroReconocimientoService {
	
	public abstract boolean addLibroReconocimiento(LibroReconocimiento libroReconocimiento);
	public abstract LibroReconocimiento findByIdLibroReconocimiento(int id);
	public abstract boolean deleteLibroReconocimiento(int id);
	public List<LibroReconocimiento> findAllLibroReconocimientos();
		

}
