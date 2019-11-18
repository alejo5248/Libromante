package com.libromante.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libromante.entity.Evento;
import com.libromante.entity.InscripcionEvento;
import com.libromante.service.EventoService;
import com.libromante.service.InscripcionService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/eventos")
public class EventoController {
	
	@Autowired
	@Qualifier("eventoserviceImpl")
	private EventoService eventoServ;
	
	@Autowired
	@Qualifier("inscripcionserviceImpl")
	private InscripcionService inscripcionServ;
	
	@GetMapping("/mostrar")
	public List<Evento> listarEventos(){
		return eventoServ.listAllEventos();
	}
	//elegir quien tiene autorizacion  
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@DeleteMapping("/inscripcion/{id}")
	public boolean cancelarInscripcion(@PathVariable("id") int id) {
		return inscripcionServ.removeInscripcion(id);
	}
	
	@PutMapping("/")
	public boolean addInscripcion(@RequestBody @Valid InscripcionEvento inscripcion) {
		return inscripcionServ.addInscripcion(inscripcion);
	}
	
	@GetMapping("/listinscripcion")
	public List<InscripcionEvento> ListarInscripciones(){
		return inscripcionServ.listAllInscripciones();
	}
	

	
	
}
