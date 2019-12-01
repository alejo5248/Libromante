package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Coleccion;

@Repository("coleccionrepository")
public interface ColeccionRepository extends JpaRepository<Coleccion, Serializable> {

	public abstract Coleccion findById(int id);

	public List<Coleccion> findAll();
}
