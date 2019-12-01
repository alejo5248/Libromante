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

import com.libromante.entity.Autor;
import com.libromante.entity.Coleccion;
import com.libromante.entity.Editorial;
import com.libromante.entity.Evento;
import com.libromante.entity.Genero;
import com.libromante.entity.Libro;
import com.libromante.entity.LibroGenero;
import com.libromante.entity.LibroReconocimiento;
import com.libromante.entity.LibroTema;
import com.libromante.entity.Pais;
import com.libromante.entity.Reconocimiento;
import com.libromante.entity.Tema;
import com.libromante.entity.Usuario;
import com.libromante.service.AutorService;
import com.libromante.service.ColeccionService;
import com.libromante.service.EditorialService;
import com.libromante.service.EventoService;
import com.libromante.service.GeneroService;
import com.libromante.service.LibroGeneroService;
import com.libromante.service.LibroReconocimientoService;
import com.libromante.service.LibroService;
import com.libromante.service.LibroTemaService;
import com.libromante.service.PaisService;
import com.libromante.service.ReconocimientoService;
import com.libromante.service.TemaService;
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
	private AutorService autorServ;
	private ColeccionService coleccionServ;
	private EditorialService editorialServ;
	private GeneroService generoServ;
	private LibroGeneroService libroGeneroServ;
	private LibroReconocimientoService libroReconocimientoServ;
	private LibroTemaService libroTemaServ;
	private PaisService paisServ;
	private ReconocimientoService reconocimientoServ;
	private TemaService temaServ;
	
	
	private static final Log LOG = LogFactory.getLog(AdminController.class);
 
	//------------------------- aqui empiezan las funciones de eventos----------------------------
	
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
			 update.setTitle(evento.getTitle());
			 update.setDescripcion(evento.getDescripcion());
			 update.setLugar(evento.getLugar());
			 update.setDate(evento.getDate());
			 update.setInscripcion(evento.isInscripcion());
			 this.eventoServ.updateEvento(update);
			
		}
		return update;	
	}
	
	@PutMapping("/evento/uploadportada")
	public ResponseEntity<?> uploadPortada(@RequestParam("archivo") MultipartFile archivo,  @RequestParam("id") int id){
		Map<String, Object> response = new HashMap<String, Object>();
		Evento evento = eventoServ.findEventoById(id);
		if(!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads/portadasEventos").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje","Error al subir la portada del evento" + nombreArchivo);
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = evento.getPortada();
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			evento.setPortada(nombreArchivo);
			eventoServ.addEvento(evento);
			response.put("evento", evento);
			response.put("mensaje","has subido correctamente la imagen" + nombreArchivo);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	//----------------------------aqui terminan las funciones de eventos----------------------------
	//--------------------aqui empiezan las funciones de usuarios---------------------------------
	@GetMapping("/mostrarusuarios")
	public List<Usuario> listarUsuarios(){
		return usuarioServ.listAllUsuarios();
	}
	
	//----------------------------aquie empiezan las funciones de libros--------------------------------------
	@PostMapping("/libro")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirLibro(@RequestBody @Valid  Libro libro) {
	
		return libroServ.addLibro(libro);	
	}	
	
	
	@PutMapping("/libros/upload")
	public ResponseEntity<?> uploadFotoLibro(@RequestParam("archivo") MultipartFile archivo,  @RequestParam("id") int id){
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
	
	@PostMapping("/coleccion")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirColeccion(@RequestBody @Valid  Coleccion coleccion) {
	
		return coleccionServ.addColeccion(coleccion);	
	}	
	
	@DeleteMapping("/coleccion/{id}")
	public boolean borrarColeccion(@PathVariable("id") int id) {
		return coleccionServ.removeColeccion(id);
	}
	
	@GetMapping("/colecciones")
	public List<Coleccion> listarColeccion(){
		return coleccionServ.findAllColecciones();
	}
	
	@PostMapping("/editorial")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirEditorial(@RequestBody @Valid  Editorial editorial) {
	
		return editorialServ.addEditorial(editorial);	
	}	
	
	@DeleteMapping("/editorial/{id}")
	public boolean borrarEditorial(@PathVariable("id") int id) {
		return editorialServ.removeEditorial(id);
	}
	
	@GetMapping("/editoriales")
	public List<Editorial> listarEditorial(){
		return editorialServ.findAllEditoriales();
	}
	
	@PostMapping("/genero")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirGenero(@RequestBody @Valid  Genero genero) {
	
		return generoServ.addGenero(genero);	
	}	
	
	@DeleteMapping("/genero/{id}")
	public boolean borrarGenero(@PathVariable("id") int id) {
		return generoServ.removeGenero(id);
	}
	
	@GetMapping("/generos")
	public List<Genero> listarGenero(){
		return generoServ.findAllGeneros();
	}
	
	@PostMapping("/librogenero")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirLibroGenero(@RequestBody @Valid  LibroGenero libroGenero) {
	
		return libroGeneroServ.addLibroGenero(libroGenero);	
	}	
	
	@DeleteMapping("/librogenero/{id}")
	public boolean borrarLibroGenero(@PathVariable("id") int id) {
		return libroGeneroServ.removeLibroGenero(id);
	}
	
	
	@PostMapping("/libroreconocimiento")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirLibroReconocimiento(@RequestBody @Valid LibroReconocimiento libroReconocimiento) {
	
		return libroReconocimientoServ.addLibroReconocimiento(libroReconocimiento);	
	}	
	
	@DeleteMapping("/libroreconocimiento/{id}")
	public boolean borrarLibroReconocimiento(@PathVariable("id") int id) {
		return libroReconocimientoServ.deleteLibroReconocimiento(id);
	}
	
	@PostMapping("/librotema")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirLibroTema(@RequestBody @Valid  LibroTema libroTema) {
	
		return libroTemaServ.addLibroTema(libroTema);	
	}	
	
	@DeleteMapping("/librotema/{id}")
	public boolean borrarLibroTema(@PathVariable("id") int id) {
		return libroTemaServ.deleteLibroTema(id);
	}
	
	@PostMapping("/pais")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirPais(@RequestBody @Valid  Pais pais) {
	
		return paisServ.addPais(pais);	
	}	
	
	@DeleteMapping("/pais/{id}")
	public boolean borrarPais(@PathVariable("id") int id) {
		return paisServ.removePais(id);
	}
	
	@GetMapping("/paises")
	public List<Pais> listarPaises(){
		return paisServ.findAllPaises();
	}
	@PostMapping("/reconocimiento")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirReconocimiento(@RequestBody @Valid  Reconocimiento reconocimiento) {
	
		return reconocimientoServ.addReconocimiento(reconocimiento);	
	}	
	
	@DeleteMapping("/reconocimiento/{id}")
	public boolean borrarReconocimiento(@PathVariable("id") int id) {
		return reconocimientoServ.removeReconocimiento(id);
	}
	
	@GetMapping("/reconocimiento")
	public List<Reconocimiento> listarReconocimiento(){
		return reconocimientoServ.findAllReconocimientos();
	}
	
	@PostMapping("/tema")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirTema(@RequestBody @Valid  Tema tema) {
	
		return temaServ.addTema(tema);	
	}	
	
	@DeleteMapping("/tema/{id}")
	public boolean borrarTema(@PathVariable("id") int id) {
		return temaServ.removeTema(id);
	}
	
	@GetMapping("/temas")
	public List<Tema> listarTemas(){
		return temaServ.findAllTemas();
	}
	
	@PostMapping("/autor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean añadirAutor(@RequestBody @Valid  Autor autor) {
	
		return autorServ.addAutor(autor);	
	}	
	
	@DeleteMapping("/autor/{id}")
	public boolean borrarAutor(@PathVariable("id") int id) {
		return autorServ.removeAutor(id);
	}
	
	@GetMapping("/autores")
	public List<Autor> listarAutor(){
		return autorServ.findAllAutores();
	}
	
}


