package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Autor;

@Repository("autorrepository")
public interface AutorRepository extends JpaRepository<Autor, Serializable> {

	public abstract Autor findById(int id);

	public List<Autor> findByNombre(String nombre);

	public List<Autor> findAll();
	
	@Query("select p from Autor p where p.nombre like %?1%")
	public List<Autor> findByNombreAutor(String nombre);

}
