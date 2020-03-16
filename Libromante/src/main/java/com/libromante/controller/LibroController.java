package com.libromante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.libromante.entity.Autor;
import com.libromante.entity.Editorial;
import com.libromante.entity.Genero;
import com.libromante.entity.Libro;
import com.libromante.entity.Tema;
import com.libromante.service.AutorService;
import com.libromante.service.EditorialService;
import com.libromante.service.GeneroService;
import com.libromante.service.LibroService;
import com.libromante.service.TemaService;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = {"http://localhost:4200"})
public class LibroController {
	@Autowired
	@Qualifier("libroserviceimpl")
	private LibroService libroServ;
	
	@Autowired
	@Qualifier("generoserviceimpl")
	private GeneroService generoServ;
	
	@Autowired
	@Qualifier("temaserviceimpl")
	private TemaService temaServ;
	
	@GetMapping("/listarpornombre/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Libro> filtrarLibros(@PathVariable String nombre){
		return libroServ.findByNombre(nombre);
	}

	
	@GetMapping("/listarportema/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Tema> filtrarTema(@PathVariable int id){
		return temaServ.findById(id);
	}
	
	@GetMapping("/listarporgenero/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Genero> filtrarGenero(@PathVariable int id){
		return generoServ.findByIdGenero(id);
	}
	

	@GetMapping("/queleer")
	public List<Libro> queLeer(){
		return libroServ.findAllLibros();
	}
	
	@GetMapping("/nuevos")
	public List<Libro> librosNuevos(){
		return libroServ.findNew();
	}
	
	@GetMapping("/portadas")
	public List<Libro> portadas(){
		return libroServ.findAllPortadas();
	}
	
	@GetMapping("/promocion")
	public List<Libro> promociones(){
		return libroServ.findAllPromocion();
	}

	
	@GetMapping("/page/{page}")
	public Page<Libro> listarPorPagina(@PathVariable Integer page){
		return libroServ.listPorPaginacion(PageRequest.of(page, 20));
	}
	
	@GetMapping("/verlibros")
	public List<Libro> verLibros(){
		return libroServ.findAll();
	}
	
	@GetMapping("/{id}")
	public List<Libro> findById(@PathVariable int id){
		return libroServ.findAllById(id);
	}
}
