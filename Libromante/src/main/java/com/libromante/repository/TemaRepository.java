package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Genero;
import com.libromante.entity.Tema;

@Repository("temarepository")
public interface TemaRepository extends JpaRepository<Tema, Serializable>{
	
	public abstract Tema findById(int id);
	
	public List<Tema> findAll();

	@Query( value = "select * from tema where id_tema =?1 ", nativeQuery = true)
	public List<Tema> findByIdTema(int id);
}
