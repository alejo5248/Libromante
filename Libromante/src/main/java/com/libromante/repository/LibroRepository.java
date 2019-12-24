package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Libro;


@Repository("librorepository")
public interface LibroRepository
		extends JpaRepository<Libro, Serializable>, PagingAndSortingRepository<Libro, Serializable> {

	public abstract Libro findById(int id);

	@Query("select p from Libro p where p.nombre like %?1%")
	public List<Libro> findByNombre(String nombre);

	// la misma manera de hacer consultas
	public List<Libro> findByNombreContainingIgnoreCase(String nombre);

	@Query(value = "SELECT * FROM Libro ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public List<Libro> findAllLibros();


	@Query(value = "SELECT * FROM Libro where bestseller = TRUE", nativeQuery = true)
	public List<Libro> findAllBetseller();

	@Query(value = "SELECT * FROM Libro ORDER BY RAND() LIMIT 8", nativeQuery = true)
	public List<Libro> findAllPortadas();

	public abstract Page<Libro> findAll(Pageable pageable);
	
	public List<Libro> findAll();
	
	@Query( value = "select * from libro where id_libro =?1 ", nativeQuery = true)
	public List<Libro> findAllId(int id);

}
