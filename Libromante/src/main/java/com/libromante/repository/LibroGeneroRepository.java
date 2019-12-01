package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.LibroGenero;

@Repository("librogenerorepository")
public interface LibroGeneroRepository extends JpaRepository<LibroGenero, Serializable> {
	
	public abstract LibroGenero findById(int id);
	
	public List<LibroGenero> findAll();

}
