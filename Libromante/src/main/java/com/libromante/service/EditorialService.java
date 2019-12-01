package com.libromante.service;

import java.util.List;

import com.libromante.entity.Editorial;

public interface EditorialService{
	
	public abstract boolean addEditorial(Editorial editorial);
	public abstract Editorial findByIdEditorial(int id);
	public abstract boolean removeEditorial(int id);
	public List<Editorial> findAllEditoriales();

}
