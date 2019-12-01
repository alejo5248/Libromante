package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Reconocimiento;

@Repository("reconocimientorepository")
public interface ReconocimientoRepository extends JpaRepository<Reconocimiento, Serializable>{
	
	public abstract Reconocimiento findById(int id);
	
	public List<Reconocimiento> findAll();

}
