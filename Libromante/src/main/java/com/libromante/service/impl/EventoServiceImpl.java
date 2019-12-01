package com.libromante.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.controller.AdminController;
import com.libromante.entity.Evento;
import com.libromante.repository.EventoRepository;
import com.libromante.service.EventoService;

@Service("eventoserviceImpl")
public class EventoServiceImpl implements EventoService{

	@Autowired
	@Qualifier("eventorepository")
	private EventoRepository eventoRep;
	
	
	
	private static final Log LOG = LogFactory.getLog(AdminController.class);
	
	@Override
	@Transactional(readOnly = true)
	public List<Evento> listAllEventos() {
		return eventoRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Evento findEventoById(int id) {
		
		return eventoRep.findById(id);
	}


	@Override
	@Transactional
	public boolean removeEvento(int id) {
		try {
			Evento evento= findEventoById(id);
			if(evento != null) {
				eventoRep.delete(evento);
			}
			return true;
		}catch(Exception e){
			return false;	
		}
		
	}


	@Override
	@Transactional
	public boolean addEvento(Evento evento) {
		try {
			eventoRep.save(evento);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateEvento(Evento evento) {
		try {
			eventoRep.save(evento);
			LOG.info("se actualizó satisfactoriamente");
			return true;
		}catch(Exception e) {
			LOG.info("hubo un error");
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> listPorPaginacion(Pageable pageable) {
		return eventoRep.findAll(pageable).getContent();
	}

	@Override
	public List<Evento> listarPorId(int id) {
		
		return eventoRep.findAllId(id);
	}

	@Override
	public List<Evento> findByTitle(String title) {
		
		return eventoRep.findByTitleContainingIgnoreCase(title);
	}

	

	
}
