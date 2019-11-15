package com.libromante.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int id;
	
	@Column(name="fecha_pedido")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "usuario_id")
	private Usuario usuario;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name= "estado_pedido_id", nullable= false)
	private EstadoPedido estadoPedido;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade =CascadeType.ALL )
	@JoinColumn(name="pedido_id")
	private List<DetPedido> detPedido;
	
	

	public Pedido() {
		detPedido = new ArrayList<DetPedido>();
	}

	public Pedido(int id, Date fecha, Usuario usuario, EstadoPedido estadoPedido) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.estadoPedido = estadoPedido;
	}
	
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	

	
	public List<DetPedido> getDetPedido() {
		return detPedido;
	}

	public void setDetPedido(List<DetPedido> detPedido) {
		this.detPedido = detPedido;
	}

	public Double getTotal() {
		Double total = 0.00;
		for (DetPedido detPedidos : detPedido) {
			total += detPedidos.getValor();
		}
		return total;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
