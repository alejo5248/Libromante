package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.libromante.entity.Tema;
import com.libromante.repository.TemaRepository;
import com.libromante.service.TemaService;

@Service("temaserviceimpl")
public class TemaServiceImpl implements TemaService{
	@Autowired
	@Qualifier("temarepository")
	TemaRepository temaRep;
	
	@Override
	@Transactional
	public boolean addTema(Tema tema) {
		try {
			temaRep.save(tema);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean removeTema(int id) {
		try {
			Tema tema = findByIdTema(id);
			if (tema != null) {
				temaRep.delete(tema);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	@Override
	@Transactional(readOnly = true)
	public Tema findByIdTema(int id) {
		
		return temaRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tema> findAllTemas() {
		
		return temaRep.findAll();
	}

}
