package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Editorial;
import com.libromante.repository.EditorialRepository;
import com.libromante.service.EditorialService;

@Service("editorialserviceimpl")
public class EditorialServiceImpl implements EditorialService{
	
	@Autowired
	@Qualifier("editorialrepository")
	EditorialRepository editorialRep;
	

	@Override
	@Transactional
	public boolean addEditorial(Editorial editorial) {
		try {
			editorialRep.save(editorial);
			return true;
		}catch(Exception e) {
		return false;
		}
	}

	

	@Override
	@Transactional
	public boolean removeEditorial(int id) {
		try {
			Editorial editorial = findByIdEditorial(id);
			if(editorial != null) {
				editorialRep.delete(editorial);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional(readOnly = true)
	public Editorial findByIdEditorial(int id) {
		
		return editorialRep.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Editorial> findAllEditoriales() {
		
		return editorialRep.findAll();
	}



	@Override
	@Transactional(readOnly = true)
	public List<Editorial> findByNombreEditorial(String nombre) {
		return editorialRep.findByNombreEditorial(nombre);
	}

}
