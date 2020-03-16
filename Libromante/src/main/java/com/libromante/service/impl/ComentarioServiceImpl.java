package com.libromante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.libromante.entity.Comentarios;
import com.libromante.repository.ComentarioRepository;
import com.libromante.service.ComentariosService;

@Service("comentarioserviceimpl")
public class ComentarioServiceImpl implements ComentariosService{

	@Autowired
	@Qualifier("comentariorepository")
	ComentarioRepository comentarioRep;
	
	@Override
	public boolean addComentario(Comentarios comentario) {
		try {
			comentarioRep.save(comentario);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean removeComentario(int id) {
		try {
			Comentarios comentario = findComentarioById(id);
			if(comentario != null) {
				comentarioRep.delete(comentario);
			}
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	@Override
	public Comentarios findComentarioById(int id) {
		// TODO Auto-generated method stub
		return comentarioRep.findById(id);
	}

}
