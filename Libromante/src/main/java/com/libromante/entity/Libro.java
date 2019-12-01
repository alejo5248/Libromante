package com.libromante.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private int precio;

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

	@Column(name = "bestseller")
	@NotEmpty(message = "no puede estar vacio")
	private boolean bestseller;

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

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "libro")
	private List<LibroGenero> generos;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "libro")
	private List<LibroTema> libroTema;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "libro")
	private List<LibroReconocimiento> libroReconocimiento;

	public Libro() {
		generos = new ArrayList<LibroGenero>();
		libroTema = new ArrayList<LibroTema>();
		libroReconocimiento = new ArrayList<LibroReconocimiento>();
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
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

	public boolean isBestseller() {
		return bestseller;
	}

	public void setBestseller(boolean bestseller) {
		this.bestseller = bestseller;
	}

	public List<LibroGenero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<LibroGenero> generos) {
		this.generos = generos;
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

	public List<LibroTema> getLibroTema() {
		return libroTema;
	}

	public void setLibroTema(List<LibroTema> libroTema) {
		this.libroTema = libroTema;
	}

	public List<LibroReconocimiento> getLibroReconocimiento() {
		return libroReconocimiento;
	}

	public void setLibroReconocimiento(List<LibroReconocimiento> libroReconocimiento) {
		this.libroReconocimiento = libroReconocimiento;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
