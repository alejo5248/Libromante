package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Libro;
import com.libromante.repository.LibroRepository;
import com.libromante.service.LibroService;

@Service("libroserviceimpl")
public class LibroServiceImpl implements LibroService{

	@Autowired
	@Qualifier("librorepository")
	LibroRepository libroRep;
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findByNombre(String nombre) {
		
		return libroRep.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findById(int id) {
		
		return libroRep.findById(id);
	}

	@Override
	@Transactional
	public boolean removeLibro(int id) {
		try {
		Libro libro = findById(id);
		if(libro != null) {
			libroRep.delete(libro);
		}
		return true;
		}catch(Exception e){
		return false;
		}
	}

	@Override
	@Transactional
	public boolean addLibro(Libro libro) {
		try {
			libroRep.save(libro);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	
	
	

}
