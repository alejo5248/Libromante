package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.libromante.entity.Libro;
@Repository("librorepository")
public interface LibroRepository extends JpaRepository<Libro, Serializable>{

	public abstract Libro findById(int id);
	
	@Query("select p from Libro p where p.nombre like %?1%")
	public List<Libro> findByNombre(String nombre); 
	//la misma manera de hacer consultas
	public List<Libro> findByNombreContainingIgnoreCase(String nombre); 
	
	
}
