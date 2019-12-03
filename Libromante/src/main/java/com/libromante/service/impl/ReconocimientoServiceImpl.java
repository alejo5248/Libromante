package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Reconocimiento;
import com.libromante.repository.ReconocimientoRepository;
import com.libromante.service.ReconocimientoService;

@Service("reconocimientoserviceimpl")
public class ReconocimientoServiceImpl implements ReconocimientoService{
	@Autowired
	@Qualifier("reconocimientorepository")
	ReconocimientoRepository reconocimientoRep;

	@Override
	@Transactional
	public boolean addReconocimiento(Reconocimiento reconocimiento) {
		try {
			reconocimientoRep.save(reconocimiento);
			return true;
		}catch(Exception e) {
		return false;
		}
	}


	@Override
	@Transactional
	public boolean removeReconocimiento(int id) {
		try {
			Reconocimiento reconocimiento = findByIdReconocimiento(id);
			if(reconocimiento != null) {
				reconocimientoRep.delete(reconocimiento);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}
	@Override
	@Transactional(readOnly = true)
	public Reconocimiento findByIdReconocimiento(int id) {
		
		return reconocimientoRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reconocimiento> findAllReconocimientos() {
		
		return reconocimientoRep.findAll();
	}
}
