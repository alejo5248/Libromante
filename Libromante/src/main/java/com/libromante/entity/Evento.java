package com.libromante.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="evento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Evento implements Serializable{

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_evento", unique=true, nullable=false)
	private int id;
	
	@Column(name="nombre", nullable=false, length=40)
	private String nombre;
	
	@Column(name="descripcion", nullable=false, length=300)
	private String descripcion;
	
	@Column(name="lugar", nullable=false, length=100)
	private String lugar;
	
	
	@Column(name="fecha", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade =CascadeType.ALL )
	@JoinColumn(name="fotos_id")
	private List<FotosEvento> fotos;
	
	@Column(name="estado")
	private boolean estado;


	
	@Column(name="inscripcion")
	private boolean inscripcion;


	

	

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public List<FotosEvento> getFotos() {
		return fotos;
	}




	public void setFotos(List<FotosEvento> fotos) {
		this.fotos = fotos;
	}




	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	

	public boolean isInscripcion() {
		return inscripcion;
	}




	public void setInscripcion(boolean inscripcion) {
		this.inscripcion = inscripcion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
