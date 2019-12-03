package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.LibroTema;
import com.libromante.repository.LibroTemaRepository;
import com.libromante.service.LibroTemaService;

@Service("librotemaserviceimpl")
public class LibroTemaServiceImpl implements LibroTemaService{
	@Autowired
	@Qualifier("librotemarepository")
	LibroTemaRepository libroTemaRep;
	
	@Override
	@Transactional
	public boolean addLibroTema(LibroTema libroTema) {
		try {
			libroTemaRep.save(libroTema);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean deleteLibroTema(int id) {
		try {
			LibroTema libroTema = findByIdLibroTema(id);
			if(libroTema != null) {
				libroTemaRep.delete(libroTema);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}
	
	@Override
	@Transactional(readOnly = true)
	public LibroTema findByIdLibroTema(int id) {
		
		return libroTemaRep.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<LibroTema> findAllLibroTemas() {
		
		return libroTemaRep.findAll();
	}

}
