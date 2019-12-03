package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Coleccion;
import com.libromante.repository.ColeccionRepository;
import com.libromante.service.ColeccionService;

@Service("coleccionserviceimpl")
public class ColeccionServiceImpl implements ColeccionService{
	
	@Autowired
	@Qualifier("coleccionrepository")
	ColeccionRepository coleccionRep;
	
	@Override
	@Transactional
	public boolean addColeccion(Coleccion coleccion) {
		try {
			coleccionRep.save(coleccion);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean removeColeccion(int id) {
		try {
			Coleccion coleccion = findByIdColeccion(id);
			if(coleccion != null) {
				coleccionRep.delete(coleccion);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Coleccion findByIdColeccion(int id) {
		
		return coleccionRep.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Coleccion> findAllColecciones() {
		
		return coleccionRep.findAll();
	}


}
