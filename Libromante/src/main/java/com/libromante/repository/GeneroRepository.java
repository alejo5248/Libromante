package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Genero;

@Repository("generorepository")
public interface GeneroRepository extends JpaRepository<Genero, Serializable>{
	
	public abstract Genero findById(int id);
	
	public List<Genero> findAll();
	
	@Query( value = "select * from genero where id_genero =?1 ", nativeQuery = true)
	public List<Genero> findAllId(int id);

}
