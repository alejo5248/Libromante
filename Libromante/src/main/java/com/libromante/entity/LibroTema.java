package com.libromante.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="libro_tema")
public class LibroTema implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_libro_tema")
	private int id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
	@JoinColumn(name= "tema_id", nullable= false)
	private Tema temas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
	@JoinColumn(name= "libro_id", nullable= false)
	private Libro libro;

	public LibroTema() {
		super();
	}

	

	public Libro getLibro() {
		return libro;
	}



	public void setLibro(Libro libro) {
		this.libro = libro;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Tema getTemas() {
		return temas;
	}

	public void setTemas(Tema temas) {
		this.temas = temas;
	}
	
	





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
