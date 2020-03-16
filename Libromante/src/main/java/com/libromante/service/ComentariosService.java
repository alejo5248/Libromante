package com.libromante.service;

import com.libromante.entity.Comentarios;

public interface ComentariosService {
	public abstract boolean addComentario(Comentarios comentario);
	public abstract boolean removeComentario(int id);
	public abstract Comentarios findComentarioById(int id);
}
