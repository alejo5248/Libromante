package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.InscripcionEvento;
import com.libromante.repository.InscripcionRepository;
import com.libromante.service.InscripcionService;

@Service("inscripcionserviceImpl")
public class InscripcionServiceImpl implements InscripcionService{

	@Autowired
	@Qualifier("inscripcionrepository")
	private InscripcionRepository inscripcionRep;


	@Override
	@Transactional
	public boolean addInscripcion(InscripcionEvento inscripcion) {
		try {
			inscripcionRep.save(inscripcion);
			return true;
		}catch(Exception e) {
			return false;
		}
	}


	@Override
	@Transactional(readOnly = true)
	public InscripcionEvento findInscripcionById(int id) {
		
		return inscripcionRep.findById(id);
	}

	@Override
	@Transactional
	public boolean removeInscripcion(int id) {
		try {
			
			InscripcionEvento inscripcion = findInscripcionById(id);
			if(inscripcion != null) {
				inscripcionRep.delete(inscripcion);
		}
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<InscripcionEvento> listAllInscripciones() {
		return inscripcionRep.findAll();
	}




	@Override
	@Transactional(readOnly = true)
	public List<InscripcionEvento> listByEvento() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional(readOnly = true)
	public List<InscripcionEvento> findAllUsuarios() {
		// TODO Auto-generated method stub
		return inscripcionRep.findAllUsuario();
	}








	
}
