package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Pais;
import com.libromante.repository.PaisRepository;
import com.libromante.service.PaisService;

@Service("paisserviceimpl")
public class PaisServiceImpl implements PaisService{

	@Autowired
	@Qualifier("paisrepository")
	PaisRepository paisRep;
	
	@Override
	@Transactional
	public boolean addPais(Pais pais) {
		try {
			paisRep.save(pais);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean removePais(int id) {
		try {
			Pais pais = findByIdPais(id);
			if(pais != null) {
				paisRep.delete(pais);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAllPaises() {
		
		return paisRep.findAll();
	}



	@Override
	public Pais findByIdPais(int id) {
		
		return paisRep.findById(id);
	}
}
