package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Genero;
import com.libromante.repository.GeneroRepository;
import com.libromante.service.GeneroService;

@Service("generoserviceimpl")
public class GeneroServiceImpl implements GeneroService{
	@Autowired
	@Qualifier("generorepository")
	GeneroRepository generoRep;

	@Override
	@Transactional
	public boolean addGenero(Genero genero) {
		try {
			generoRep.save(genero);
			return true;
		}catch(Exception e) {
		return false;
		}
	}

	

	@Override
	@Transactional
	public boolean removeGenero(int id) {
		try {
			Genero genero = findById(id);
			if(genero != null) {
				generoRep.delete(genero);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genero> findAllGeneros() {
		
		return generoRep.findAll();
	}



	@Override
	@Transactional(readOnly = true)
	public Genero findById(int id) {
		return generoRep.findById(id);
	}



	@Override
	public List<Genero> findByIdGenero(int id) {
		return generoRep.findAllId(id);
	}



	

	
	
	

}
