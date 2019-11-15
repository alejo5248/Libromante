package com.libromante.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="genero")
public class Genero implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id_genero")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	
	
	public Genero() {
		
	}

	public Genero(int id, String nombre) {
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



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
}
