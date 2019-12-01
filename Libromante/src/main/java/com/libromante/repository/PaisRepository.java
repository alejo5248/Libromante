package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Pais;

@Repository("paisrepository")
public interface PaisRepository extends JpaRepository<Pais, Serializable> {
	
	public abstract Pais findById(int id);

	public List<Pais> findAll();
}
