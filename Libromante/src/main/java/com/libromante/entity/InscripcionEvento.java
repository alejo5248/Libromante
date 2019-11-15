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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inscripcion_evento")
public class InscripcionEvento implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_inscripcion_evento")
	private int id;
	
	@NotNull(message = "ingrese un evento")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name= "evento_id", nullable=false)
	private Evento evento;
	
	@NotNull(message = "ingrese un usuario")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"inscripcion","hibernateLazyInitializer", "handler"})
	@JoinColumn(name= "usuario_id")
	private Usuario usuario;

	public InscripcionEvento() {
		super();
	}

	public InscripcionEvento(int id, Evento evento, Usuario usuario) {
		super();
		this.id = id;
		this.evento = evento;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
}
