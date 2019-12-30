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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="autor")
public class Autor implements Serializable{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_autor")
	private int id;
	
	@NotEmpty(message = "ingrese el nombre de autor")
	@Column(name="nombre")
	private String nombre;
	
	@NotEmpty(message = "ingrese la biografia")
	@Column(name="biografia", length = 8000)
	private String biografia;
	
	@NotNull(message = "el pais no puede ser vacio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
	@JoinColumn(name= "pais_id", nullable= false)
	private Pais pais;
	
	@JsonIgnoreProperties(value= {"autor","hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Libro> libros;
	

	public Autor() {
		this.libros = new ArrayList<Libro>();
	}

	public Autor(int id, String nombre, String biografia, Pais pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.biografia = biografia;
		this.pais = pais;
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

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
