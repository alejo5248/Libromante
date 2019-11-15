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
@Table(name="libro_genero")
public class LibroGenero implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_libro_genero")
	private int id;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
	@JoinColumn(name= "genero_id", nullable= false)
	private Genero genero;
	
	
	
	public LibroGenero() {
		super();
	}
	

	public LibroGenero(int id, Genero genero) {
		super();
		this.id = id;
		this.genero = genero;
	}

	




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}






	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
