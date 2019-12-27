package com.libromante.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "libro")
public class Libro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro")
	private int id;

	@Column(name = "nombre")
	@NotEmpty(message = "El nombre no puede estar vacio")
	private String nombre;

	@Column(name = "precio")
	@NotEmpty(message = "por favor ingrese el precio")
	private Double precio;

	@Column(name = "paginas")
	@NotEmpty(message = "ingrese el numero de paginas")
	private int paginas;

	@Column(name = "edicion")
	@NotEmpty(message = "agregue la edicion")
	private String edicion;

	@Column(name = "formato")
	@NotEmpty(message = "agregue el formato")
	private String formato;

	@Column(name = "sipnosis", length = 1500)
	@NotEmpty(message = "escriba la sipnosis")
	private String sipnosis;

	@Column(name = "idioma")
	@NotEmpty(message = "ingrese el idioma")
	private String idioma;

	@Column(name = "fecha_publicacion")
	@Temporal(TemporalType.DATE)
	private Date fechaPublicacion;

	@Column(name = "foto")
	private String foto;

	@Column(name = "isbn")
	@NotEmpty(message = "no puede estar vacio")
	private String isbn;
	
	@Column(name="promoción")
	private boolean promocion;
	
	@Column(name="descuento_promocion")
	private int descuento;
	
	@Column(name="precio_promoción")
	private Double precioPromocion;

	@NotNull(message = "el autor no puede ser nulo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id")
	@JsonIgnoreProperties({ "libros", "hibernateLazyInitializer", "handler" })
	private Autor autor;

	@NotNull(message = "ingrese un editorial")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "libros", "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "editorial_id")
	private Editorial editorial;

	@NotNull(message = "ingrese una coleccion")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "coleccion_id")
	private Coleccion coleccion;
	
	@NotNull(message = "el genero no puede ser nulo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genero_id")
	@JsonIgnoreProperties({ "libros", "hibernateLazyInitializer", "handler" })
	private Genero genero;
	
	@NotNull(message = "el tema no puede ser nulo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tema_id")
	@JsonIgnoreProperties({ "libros", "hibernateLazyInitializer", "handler" })
	private Tema tema;
	
	@NotNull(message = "el reconocimiento no puede ser nulo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reconocimiento_id")
	@JsonIgnoreProperties({ "libros", "hibernateLazyInitializer", "handler" })
	private Reconocimiento reconocimiento;
	

	
	public Libro() {
		}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
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


	public Double getPrecio() {
		return precio * (getDescuento()/100);
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public boolean isPromocion() {
		return promocion;
	}

	public void setPromocion(boolean promocion) {
		this.promocion = promocion;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public Double getPrecioPromocion() {
		return precio;
	}


	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getSipnosis() {
		return sipnosis;
	}

	public void setSipnosis(String sipnosis) {
		this.sipnosis = sipnosis;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Coleccion getColeccion() {
		return coleccion;
	}

	public void setColeccion(Coleccion coleccion) {
		this.coleccion = coleccion;
	}


	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
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
