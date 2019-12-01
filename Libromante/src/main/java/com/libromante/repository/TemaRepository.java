package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Tema;

@Repository("temarepository")
public interface TemaRepository extends JpaRepository<Tema, Serializable>{
	
	public abstract Tema findById(int id);
	
	public List<Tema> findAll();

}
