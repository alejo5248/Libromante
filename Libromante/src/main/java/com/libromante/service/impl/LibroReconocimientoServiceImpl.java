package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.LibroReconocimiento;
import com.libromante.repository.LibroReconocimientoRepository;
import com.libromante.service.LibroReconocimientoService;

@Service("libroreconocimientoserviceimpl")
public class LibroReconocimientoServiceImpl implements LibroReconocimientoService{
	@Autowired
	@Qualifier("libroreconocimientorepository")
	LibroReconocimientoRepository libroRecRep;
	
	@Override
	@Transactional
	public boolean addLibroReconocimiento(LibroReconocimiento libroReconocimiento) {
		try {
			libroRecRep.save(libroReconocimiento);
			return true;
		}catch(Exception e) {
		return false;
		}
	}


	@Override
	@Transactional
	public boolean deleteLibroReconocimiento(int id) {
		try {
			LibroReconocimiento libroReconocimiento = findByIdLibroReconocimiento(id);
			if(libroReconocimiento != null) {
				libroRecRep.delete(libroReconocimiento);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}
	
	@Override
	@Transactional(readOnly = true)
	public LibroReconocimiento findByIdLibroReconocimiento(int id) {
		
		return libroRecRep.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<LibroReconocimiento> findAllLibroReconocimientos() {
		
		return libroRecRep.findAll();
	}
}
