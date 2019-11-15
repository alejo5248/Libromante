package com.libromante.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="editorial")
public class Editorial implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_editorial")
	private int id;
	
	@NotEmpty(message = "agregue un nombre de editorial")
	@Column(name="nombre")
	private String nombre;
	
	@JsonIgnoreProperties( {"editorial","hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "editorial", cascade = CascadeType.ALL)
	private List<Libro> libros;
	
	public Editorial() {
		this.libros = new ArrayList<Libro>();
	}

	public Editorial(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
