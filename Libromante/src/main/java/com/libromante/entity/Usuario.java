package com.libromante.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int id;
	
	@Column(name="nombre")
	@NotEmpty(message = "ingrese un nombre")
	@Size(min = 4, max=45)
	private String nombre;
	
	@Column(name="apellido")
	@NotEmpty(message = "ingrese los apellidos")
	@Size(min = 4, max=45)
	private String apellido;

	@Email(message = "no es una direccion de correo correcta")
	@Column(name="email")
	@NotEmpty(message = "no puede estar vacio")
	private String email;

	@Column(name="direccion")
	@NotEmpty(message = "agregue una direccion")
	@Size(min = 10, max=100)
	private String direccion;

	@Column(name="username", unique = true)
	@NotEmpty(message = "el nombre de usuario no puede estar vacio")
	@Size(min = 4, max=45)
	private String username;
	
	@Column(name="password")
	@NotEmpty(message = "la contraseña no puede estar vacia")
	@Size(min = 8, max=70)
	private String password;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roles;
	
	@JsonIgnoreProperties( {"usuario","hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<InscripcionEvento> inscripcion;
	
	
	public Usuario() {
		this.inscripcion = new ArrayList<InscripcionEvento>();
	}

	public Usuario(int id, String nombre, String apellido, @Email String email, String direccion,
			String username) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.username = username;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<InscripcionEvento> getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(List<InscripcionEvento> inscripcion) {
		this.inscripcion = inscripcion;
	}


	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
