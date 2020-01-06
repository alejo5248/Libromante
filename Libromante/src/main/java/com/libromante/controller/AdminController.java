package com.libromante.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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
import com.libromante.entity.FotosEvento;
import com.libromante.entity.Genero;
import com.libromante.entity.Libro;
import com.libromante.entity.Pais;
import com.libromante.entity.Reconocimiento;
import com.libromante.entity.Tema;
import com.libromante.entity.Usuario;
import com.libromante.service.AutorService;
import com.libromante.service.ColeccionService;
import com.libromante.service.EditorialService;
import com.libromante.service.EventoService;
import com.libromante.service.FotosEventoService;
import com.libromante.service.GeneroService;
import com.libromante.service.LibroService;
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
	@Qualifier("fotoseventoserviceimpl")
	private FotosEventoService fotosEventoServ;
	
	@Autowired
	@Qualifier("usuarioserviceImpl")
	private UsuarioService usuarioServ;
	
	@Autowired
	@Qualifier("libroserviceimpl")
	private LibroService libroServ;
	
	
	@Autowired
	@Qualifier("generoserviceimpl")
	private GeneroService generoServ;
	
	@Autowired
	@Qualifier("autorserviceimpl")
	private AutorService autorServ;
	
	@Autowired
	@Qualifier("coleccionserviceimpl")
	private ColeccionService coleccionServ;
	
	@Autowired
	@Qualifier("editorialserviceimpl")
	private EditorialService editorialServ;
	
	
	@Autowired
	@Qualifier("paisserviceimpl")
	private PaisService paisServ;
	
	@Autowired
	@Qualifier("reconocimientoserviceimpl")
	private ReconocimientoService reconocimientoServ;
	
	@Autowired
	@Qualifier("temaserviceimpl")
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
		Evento evento = eventoServ.findEventoById(id);
		String nombreFotoAnterior = evento.getPortada();
		if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
			Path rutaFotoAnterior = Paths.get(nombreFotoAnterior).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
			}
		}
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
	

	@PutMapping("evento/fotos")
	public FotosEvento agregarFotos(@RequestParam("fotosEvento") FotosEvento fotosEvento, @RequestParam("archivo") MultipartFile archivo) {
		Map<String, Object> response = new HashMap<>();
		String nombreArchivo = null;
		fotosEvento.setEvento(fotosEvento.getEvento());
		try {
			nombreArchivo= fotosEventoServ.copiar(archivo);
			
		}catch(IOException e) {
			response.put("mensaje", "Error al subir la imagen del evento");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
		}
		fotosEvento.setFoto(nombreArchivo);
		this.fotosEventoServ.addFotosEvento(fotosEvento);
		return fotosEvento;
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
	
	@GetMapping("/portadas/{nombreFoto:.+}")
	public ResponseEntity<Resource> verPortadaEvento(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads/portadasEventos").resolve(nombreFoto).toAbsolutePath();
		Resource recurso= null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen" + nombreFoto);
			
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera,  HttpStatus.OK);
	}
	
	
	//----------------------------aqui terminan las funciones de eventos----------------------------
	//--------------------aqui empiezan las funciones de usuarios---------------------------------
	@GetMapping("/mostrarusuarios")
	public List<Usuario> listarUsuarios(){
		return usuarioServ.listAllUsuarios();
	}
	
	//----------------------------aqui empiezan las funciones de libros--------------------------------------
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
	
	@GetMapping("/uploads/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso= null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen" + nombreFoto);
			
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera,  HttpStatus.OK);
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
	
	@PutMapping("libro/modificar/{id}")
	public Libro updateLibro(@PathVariable("id") int id, @RequestBody @Valid Libro libro) {
		Libro update = libroServ.findById(id);
		if(id != 0) {
			update.setAutor(libro.getAutor());
			update.setColeccion(libro.getColeccion());
			update.setEdicion(libro.getEdicion());
			update.setEditorial(libro.getEditorial());
			update.setFechaPublicacion(libro.getFechaPublicacion());
			update.setFormato(libro.getFormato());
			update.setIdioma(libro.getIdioma());
			update.setNombre(libro.getNombre());
			update.setPaginas(libro.getPaginas());
			update.setPrecio(libro.getPrecio());
			update.setSipnosis(libro.getSipnosis());
			update.setDescuento(libro.getDescuento());
			update.setGenero(libro.getGenero());
			update.setIsbn(libro.getIsbn());
			update.setPromocion(libro.isPromocion());
			update.setReconocimiento(libro.getReconocimiento());
			update.setTema(libro.getTema());
			this.libroServ.addLibro(update);
		}
		return update;
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
	
	@PutMapping("coleccion/modificar/{id}")
	public Coleccion updateColeccion(@PathVariable("id") int id, @RequestBody @Valid Coleccion coleccion) {
		Coleccion update = coleccionServ.findByIdColeccion(id);
		if(id != 0) {
			update.setNombre(coleccion.getNombre());
			this.coleccionServ.addColeccion(update);
		}
		return update;
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
	
	@PutMapping("editorial/modificar/{id}")
	public Editorial updateEditorial(@PathVariable("id") int id, @RequestBody @Valid Editorial editorial) {
		Editorial update = editorialServ.findByIdEditorial(id);
		if(id != 0) {
			update.setNombre(editorial.getNombre());
			this.editorialServ.addEditorial(update);
		}
		return update;
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
	
	@PutMapping("genero/modificar/{id}")
	public Genero updateGenero(@PathVariable("id") int id, @RequestBody @Valid Genero genero) {
		Genero update = generoServ.findByIdGenero(id);
		if(id != 0) {
			update.setNombre(genero.getNombre());
			this.generoServ.addGenero(update);
		}
		return update;
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
	
	@PutMapping("pais/modificar/{id}")
	public Pais updatePais(@PathVariable("id") int id, @RequestBody @Valid Pais pais) {
		Pais update = paisServ.findByIdPais(id);
		if(id != 0) {
			update.setNombre(pais.getNombre());
			update.setSiglas(pais.getSiglas());
			this.paisServ.addPais(update);
		}
		return update;
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
	
	@PutMapping("reconocimiento/modificar/{id}")
	public Reconocimiento updateReconocimiento(@PathVariable("id") int id, @RequestBody @Valid Reconocimiento reconocimiento) {
		Reconocimiento update = reconocimientoServ.findByIdReconocimiento(id);
		if(id != 0) {
			update.setNombre(reconocimiento.getNombre());
			this.reconocimientoServ.addReconocimiento(update);
		}
		return update;
	}
	
	@GetMapping("/reconocimientos")
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
	
	@PutMapping("tema/modificar/{id}")
	public Tema updateTema(@PathVariable("id") int id, @RequestBody @Valid Tema tema) {
		Tema update = temaServ.findByIdTema(id);
		if(id != 0) {
			update.setNombre(tema.getNombre());
			this.temaServ.addTema(update);
		}
		return update;
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
	
	@PutMapping("autor/modificar/{id}")
	public Autor updateAutor(@PathVariable("id") int id, @RequestBody @Valid Autor autor) {
		Autor update = autorServ.findByIdAutor(id);
		if(id != 0) {
			update.setNombre(autor.getNombre());
			update.setBiografia(autor.getBiografia());
			update.setPais(autor.getPais());
			this.autorServ.addAutor(update);
		}
		return update;
	}
	
	@GetMapping("/autores")
	public List<Autor> listarAutor(){
		return autorServ.findAllAutores();
	}
	
}


