package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Autor;
import com.libromante.entity.Coleccion;
import com.libromante.entity.Editorial;
import com.libromante.entity.Genero;
import com.libromante.entity.Libro;
import com.libromante.entity.LibroGenero;
import com.libromante.entity.LibroReconocimiento;
import com.libromante.entity.LibroTema;
import com.libromante.entity.Pais;
import com.libromante.entity.Reconocimiento;
import com.libromante.entity.Tema;
import com.libromante.repository.AutorRepository;
import com.libromante.repository.ColeccionRepository;
import com.libromante.repository.EditorialRepository;
import com.libromante.repository.GeneroRepository;
import com.libromante.repository.LibroGeneroRepository;
import com.libromante.repository.LibroReconocimientoRepository;
import com.libromante.repository.LibroRepository;
import com.libromante.repository.LibroTemaRepository;
import com.libromante.repository.PaisRepository;
import com.libromante.repository.ReconocimientoRepository;
import com.libromante.repository.TemaRepository;
import com.libromante.service.AutorService;
import com.libromante.service.ColeccionService;
import com.libromante.service.EditorialService;
import com.libromante.service.GeneroService;
import com.libromante.service.LibroGeneroService;
import com.libromante.service.LibroReconocimientoService;
import com.libromante.service.LibroService;
import com.libromante.service.LibroTemaService;
import com.libromante.service.PaisService;
import com.libromante.service.ReconocimientoService;
import com.libromante.service.TemaService;

@Service("libroserviceimpl")
public class LibroServiceImpl implements LibroService, PaisService, AutorService, ColeccionService, EditorialService, GeneroService, LibroGeneroService, LibroReconocimientoService, LibroTemaService, TemaService, ReconocimientoService {

	@Autowired
	@Qualifier("librorepository")
	LibroRepository libroRep;
	
	@Autowired
	@Qualifier("autorrepository")
	AutorRepository autorRep;
	
	@Autowired
	@Qualifier("coleccionrepository")
	ColeccionRepository coleccionRep;
	
	@Autowired
	@Qualifier("editorialrepository")
	EditorialRepository editorialRep;
	
	@Autowired
	@Qualifier("generorepository")
	GeneroRepository generoRep;
	
	@Autowired
	@Qualifier("librogenerorepository")
	LibroGeneroRepository libroGeneroRep;
	
	@Autowired
	@Qualifier("libroreconocimientorepository")
	LibroReconocimientoRepository libroRecRep;
	
	@Autowired
	@Qualifier("librotemarepository")
	LibroTemaRepository libroTemaRep;
	
	@Autowired
	@Qualifier("paisrepository")
	PaisRepository paisRep;
	
	@Autowired
	@Qualifier("reconocimientorepository")
	ReconocimientoRepository reconocimientoRep;
	
	@Autowired
	@Qualifier("temarepository")
	TemaRepository temaRep;
	
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findByNombre(String nombre) {
		
		return libroRep.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findById(int id) {
		
		return libroRep.findById(id);
	}

	@Override
	@Transactional
	public boolean removeLibro(int id) {
		try {
		Libro libro = findById(id);
		if(libro != null) {
			libroRep.delete(libro);
		}
		return true;
		}catch(Exception e){
		return false;
		}
	}

	@Override
	@Transactional
	public boolean addLibro(Libro libro) {
		try {
			libroRep.save(libro);
			return true;
		}catch(Exception e) {
		return false;
		}
	}

	@Override
	public List<Libro> findAllLibros() {
		
		return libroRep.findAllLibros();
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibroGenero> findByGenero(int id) {
		
		return libroRep.findByGenero(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAllBetseller() {
		
		return libroRep.findAllBetseller();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAllPortadas() {
		return libroRep.findAllPortadas();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> listPorPaginacion(Pageable pageable) {
		
		return libroRep.findAll(pageable);
	}

	@Override
	@Transactional
	public boolean removeAutor(int id) {
		try {
			Autor autor = findByIdAutor(id);
			if(autor != null) {
				autorRep.delete(autor);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addAutor(Autor autor) {
		try {
			autorRep.save(autor);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean addPais(Pais pais) {
		try {
			paisRep.save(pais);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean removePais(int id) {
		try {
			Pais pais = findByIdPais(id);
			if(pais != null) {
				paisRep.delete(pais);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addColeccion(Coleccion coleccion) {
		try {
			coleccionRep.save(coleccion);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean removeColeccion(int id) {
		try {
			Coleccion coleccion = findByIdColeccion(id);
			if(coleccion != null) {
				coleccionRep.delete(coleccion);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addEditorial(Editorial editorial) {
		try {
			editorialRep.save(editorial);
			return true;
		}catch(Exception e) {
		return false;
		}
	}

	

	@Override
	@Transactional
	public boolean removeEditorial(int id) {
		try {
			Editorial editorial = findByIdEditorial(id);
			if(editorial != null) {
				editorialRep.delete(editorial);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addGenero(Genero genero) {
		try {
			generoRep.save(genero);
			return true;
		}catch(Exception e) {
		return false;
		}
	}

	

	@Override
	@Transactional
	public boolean removeGenero(int id) {
		try {
			Genero genero = findByIdGenero(id);
			if(genero != null) {
				generoRep.delete(genero);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addLibroGenero(LibroGenero libroGenero) {
		try {
			libroGeneroRep.save(libroGenero);
			return true;
		}catch(Exception e) {
		return false;
		}
	}


	@Override
	@Transactional
	public boolean removeLibroGenero(int id) {
		try {
			LibroGenero libroGenero = findByIdLibroGenero(id);
			if(libroGenero != null) {
				libroGeneroRep.delete(libroGenero);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addLibroReconocimiento(LibroReconocimiento libroReconocimiento) {
		try {
			libroRecRep.save(libroReconocimiento);
			return true;
		}catch(Exception e) {
		return false;
		}
	}


	@Override
	@Transactional
	public boolean deleteLibroReconocimiento(int id) {
		try {
			LibroReconocimiento libroReconocimiento = findByIdLibroReconocimiento(id);
			if(libroReconocimiento != null) {
				libroRecRep.delete(libroReconocimiento);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addLibroTema(LibroTema libroTema) {
		try {
			libroTemaRep.save(libroTema);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean deleteLibroTema(int id) {
		try {
			LibroTema libroTema = findByIdLibroTema(id);
			if(libroTema != null) {
				libroTemaRep.delete(libroTema);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addReconocimiento(Reconocimiento reconocimiento) {
		try {
			reconocimientoRep.save(reconocimiento);
			return true;
		}catch(Exception e) {
		return false;
		}
	}


	@Override
	@Transactional
	public boolean removeReconocimiento(int id) {
		try {
			Reconocimiento reconocimiento = findByIdReconocimiento(id);
			if(reconocimiento != null) {
				reconocimientoRep.delete(reconocimiento);
			}
			return true;
			}catch(Exception e){
			return false;
			}
	}

	@Override
	@Transactional
	public boolean addTema(Tema tema) {
		try {
			temaRep.save(tema);
			return true;
		}catch(Exception e) {
		return false;
		}
	}



	@Override
	@Transactional
	public boolean removeTema(int id) {
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Reconocimiento findByIdReconocimiento(int id) {
		
		return reconocimientoRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Tema findByIdTema(int id) {
		
		return temaRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public LibroTema findByIdLibroTema(int id) {
		
		return libroTemaRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public LibroReconocimiento findByIdLibroReconocimiento(int id) {
		
		return libroRecRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public LibroGenero findByIdLibroGenero(int id) {
		
		return libroGeneroRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Genero findByIdGenero(int id) {
		
		return generoRep.finById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Editorial findByIdEditorial(int id) {
		
		return editorialRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Coleccion findByIdColeccion(int id) {
		
		return coleccionRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Autor findByIdAutor(int id) {
		
		return autorRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pais findByIdPais(int id) {
		
		return paisRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reconocimiento> findAllReconocimientos() {
		
		return reconocimientoRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tema> findAllTemas() {
		
		return temaRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibroTema> findAllLibroTemas() {
		
		return libroTemaRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibroReconocimiento> findAllLibroReconocimientos() {
		
		return libroRecRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibroGenero> findAllLibroGeneros() {
		
		return libroGeneroRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genero> findAllGeneros() {
		
		return generoRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Editorial> findAllEditoriales() {
		
		return editorialRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Coleccion> findAllColecciones() {
		
		return coleccionRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Autor> findAllAutores() {
		
		return autorRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAllPaises() {
		
		return paisRep.findAll();
	}


}
