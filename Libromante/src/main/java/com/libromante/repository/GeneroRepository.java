package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Genero;

@Repository("generorepository")
public interface GeneroRepository extends JpaRepository<Genero, Serializable>{
	
	public abstract Genero findById(int id);
	
	public List<Genero> findAll();

}
