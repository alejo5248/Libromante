package com.libromante.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pais")
public class Pais implements Serializable{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pais")
	private int id;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="siglas")
	private String siglas;


	
	public Pais() {
		super();
	}
	

	public Pais(int id, String nombre, String siglas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.siglas = siglas;
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

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
