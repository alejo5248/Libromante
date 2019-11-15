package com.libromante.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tema")
public class Tema implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tema")
	private int id;
	
	@Column(name="nombre")
	@NotEmpty(message = "ingrese un tema")
	private String nombre;
	
	
	public Tema() {
		super();
	}
	

	public Tema(int id, String nombre) {
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
