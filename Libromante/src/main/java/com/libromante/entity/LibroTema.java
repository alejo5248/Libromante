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

	public LibroTema() {
		super();
	}

	public LibroTema(int id,  Tema temas) {
		super();
		this.id = id;
		
		this.temas = temas;
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
