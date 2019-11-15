package com.libromante.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Evento;

@Repository("eventorepository")
public interface EventoRepository extends JpaRepository<Evento, Serializable>, PagingAndSortingRepository<Evento, Serializable> {

	public abstract Evento findByNombre(String nombre);
	public abstract List<Evento> findByFecha(Date fecha);
	public abstract Evento findById(int id);
	public abstract Evento findByNombreAndId(String nombre, int id);
	public abstract Page<Evento> findAll(Pageable pageable);
	
	
	

}
