package com.libromante.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.libromante.entity.Evento;


public interface EventoService {
	
	public abstract List<Evento> listAllEventos();
	public abstract Evento findEventoById(int id);
	public abstract boolean removeEvento(int id);
	public abstract boolean addEvento(Evento evento);
	public abstract boolean updateEvento(Evento evento);
	public abstract List<Evento> listPorPaginacion(Pageable pageable);
	
	
}
