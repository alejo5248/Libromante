package com.libromante.repository;

import java.io.Serializable;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libromante.entity.InscripcionEvento;

@Repository("inscripcionrepository")
public interface InscripcionRepository extends JpaRepository<InscripcionEvento, Serializable> {
	
	public abstract InscripcionEvento  findById(int id);
	public abstract List<InscripcionEvento> findByEvento(int evento);
	@Query("from InscripcionEvento")
	public abstract List<InscripcionEvento> findAllUsuario();
	

}
