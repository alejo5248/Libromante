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
@Table(name="det_pedido")
public class DetPedido implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_det_pedido")
	private int id;
	

	@Column(name="cantidad")
	private Integer cantidad;
	
	@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "libro_id")
	private Libro libros;
	
	

	public DetPedido() {
		super();
	}

	public DetPedido(int id, int cantidad, Libro libros) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.libros = libros;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Libro getLibros() {
		return libros;
	}

	public void setLibros(Libro libros) {
		this.libros = libros;
	}

	public Double getValor() {
		return cantidad.doubleValue() * libros.getPrecio();
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
}
