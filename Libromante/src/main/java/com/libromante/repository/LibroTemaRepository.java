package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.LibroTema;

@Repository("librotemarepository")
public interface LibroTemaRepository extends JpaRepository<LibroTema, Serializable>{

	public abstract LibroTema findById(int id);
	
	public List<LibroTema> findAll();
}
