package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.LibroGenero;
import com.libromante.repository.LibroGeneroRepository;
import com.libromante.service.LibroGeneroService;

@Service("librogeneroserviceimpl")
public class LibroGeneroServiceImpl implements LibroGeneroService{
	@Autowired
	@Qualifier("librogenerorepository")
	LibroGeneroRepository libroGeneroRep;
	
	@Override
	@Transactional
	public boolean addLibroGenero(LibroGenero libroGenero) {
		try {
			libroGeneroRep.save(libroGenero);
			return true;
		}catch(Exception e) {
		return false;
		}
	}


	@Override
	@Transactional
	public boolean removeLibroGenero(int id) {
		try {
			LibroGenero libroGenero = findByIdLibroGenero(id);
			if(libroGenero != null) {
				libroGeneroRep.delete(libroGenero);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}
	
	@Override
	@Transactional(readOnly = true)
	public LibroGenero findByIdLibroGenero(int id) {
		
		return libroGeneroRep.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<LibroGenero> findAllLibroGeneros() {
		
		return libroGeneroRep.findAll();
	}

}
