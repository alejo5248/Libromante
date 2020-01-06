package com.libromante.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.libromante.entity.FotosEvento;
import com.libromante.repository.FotosEventoRepository;
import com.libromante.service.FotosEventoService;

@Service("fotoseventoserviceimpl")
public class FotosEventoServiceImpl implements FotosEventoService{
	
	@Autowired
	@Qualifier("fotoseventorepository")
	private FotosEventoRepository fotosEventoRep;
	
	private final static String DIRECTORIO_FOTOS = "uploads/fotosEventos";

	@Override
	@Transactional
	public boolean addFotosEvento(FotosEvento fotosEvento) {
		try {
			fotosEventoRep.save(fotosEvento);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso= null;
		
		recurso = new UrlResource(rutaArchivo.toUri());
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen" + nombreFoto);
			
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return recurso;
		
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo);
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		
		return nombreArchivo;
	

	}

	@Override
	public boolean eliminar(String nombreFoto) {
	
		if(nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaFotoAnterior = Paths.get(nombreFoto).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		
		return Paths.get(DIRECTORIO_FOTOS).resolve(nombreFoto).toAbsolutePath();
	}
	
	

}
