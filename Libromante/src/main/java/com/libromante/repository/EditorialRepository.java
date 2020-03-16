package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Editorial;

@Repository("editorialrepository")
public interface EditorialRepository extends JpaRepository<Editorial, Serializable> {

	public abstract Editorial findById(int id);

	public List<Editorial> findAll();

	@Query("select p from Editorial p where p.nombre like %?1%")
	public List<Editorial> findByNombreEditorial(String nombre);
}
