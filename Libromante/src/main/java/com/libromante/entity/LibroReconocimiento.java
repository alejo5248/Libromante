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
@Table(name="libro_reconocimiento")
public class LibroReconocimiento implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_libro_reconocimiento")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
	@JoinColumn(name= "reconocimiento_id", nullable= false)
	private Reconocimiento reconocimiento;

	public LibroReconocimiento() {
		super();
	}

	public LibroReconocimiento(int id, Reconocimiento reconocimiento) {
		super();
		this.id = id;
		this.reconocimiento = reconocimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Reconocimiento getReconocimiento() {
		return reconocimiento;
	}

	public void setReconocimiento(Reconocimiento reconocimiento) {
		this.reconocimiento = reconocimiento;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
