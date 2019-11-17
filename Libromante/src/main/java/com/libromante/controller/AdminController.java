package com.libromante.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.libromante.entity.Evento;
import com.libromante.entity.Libro;
import com.libromante.entity.Usuario;
import com.libromante.service.EventoService;
import com.libromante.service.LibroService;
import com.libromante.service.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	@Qualifier("eventoserviceImpl")
	private EventoService eventoServ;
	
	@Autowired
	@Qualifier("usuarioserviceImpl")
	private UsuarioService usuarioServ;
	
	@Autowired
	@Qualifier("libroserviceimpl")
	private LibroService libroServ;
	
	
	private static final Log LOG = LogFactory.getLog(AdminController.class);
	
	
	@PostMapping("/evento")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirEvento(@RequestBody @Valid  Evento evento) {
	
		return eventoServ.addEvento(evento);	
	}	
	
	@DeleteMapping("/evento/{id}")
	public boolean borrarEvento(@PathVariable("id") int id) {
		return eventoServ.removeEvento(id);
	}
	
	@PutMapping("eventoform/{id}")
	public Evento actualizarEvento(@RequestBody Evento evento, @Valid @PathVariable(name="id", required=true) int id) {
		Evento update = this.eventoServ.findEventoById(id);
		if(id!=0) {
			LOG.info("accediendo al id");
			 update.setNombre(evento.getNombre());
			 update.setDescripcion(evento.getDescripcion());
			 update.setLugar(evento.getLugar());
			 update.setFecha(evento.getFecha());
			 update.setEstado(evento.isEstado());
			 this.eventoServ.updateEvento(update);
			
		}
		return update;	
	}
	
	@GetMapping("/mostrarusuarios")
	public List<Usuario> listarUsuarios(){
		return usuarioServ.listAllUsuarios();
	}
	
	@PutMapping("/libros/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,  @RequestParam("id") int id){
		Map<String, Object> response = new HashMap<String, Object>();
		Libro libro = libroServ.findById(id);
		if(!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje","Error al subir la imagen del libro" + nombreArchivo);
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = libro.getFoto();
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			libro.setFoto(nombreArchivo);
			libroServ.addLibro(libro);
			response.put("libro", libro);
			response.put("mensaje","has subido correctamente la imagen" + nombreArchivo);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/libro/{id}")
	public boolean eliminarLibro(int id) {
		Libro libro = libroServ.findById(id);
		String nombreFotoAnterior = libro.getFoto();
		if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
			Path rutaFotoAnterior = Paths.get(nombreFotoAnterior).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
			}
		}
		
		return libroServ.removeLibro(id);
	}
	
	
}


