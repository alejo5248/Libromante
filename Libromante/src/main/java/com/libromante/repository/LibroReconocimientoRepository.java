package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.LibroReconocimiento;

@Repository("libroreconocimientorepository")
public interface LibroReconocimientoRepository extends JpaRepository<LibroReconocimiento, Serializable>{
	
	public abstract LibroReconocimiento findById(int id);

	public List<LibroReconocimiento> findAll();
}
