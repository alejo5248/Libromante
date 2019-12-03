package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Autor;
import com.libromante.repository.AutorRepository;
import com.libromante.service.AutorService;

@Service("autorserviceimpl")
public class AutorServiceImpl implements AutorService{

	@Autowired
	@Qualifier("autorrepository")
	AutorRepository autorRep;

	@Override
	@Transactional
	public boolean removeAutor(int id) {
		try {
			Autor autor = findByIdAutor(id);
			if(autor != null) {
				autorRep.delete(autor);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addAutor(Autor autor) {
		try {
			autorRep.save(autor);
			return true;
		}catch(Exception e) {
		return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Autor findByIdAutor(int id) {
		
		return autorRep.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Autor> findAllAutores() {
		
		return autorRep.findAll();
	}
}
