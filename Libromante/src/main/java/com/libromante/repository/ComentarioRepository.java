package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Comentarios;
import com.libromante.entity.Post;

@Repository("comentariorepository")
public interface ComentarioRepository extends JpaRepository<Comentarios, Serializable>{
	Comentarios save(Post e);
	@Query( value = "select * from comentarios where post_id =?1 ", nativeQuery = true)
	public List<Comentarios> findAllPostId(int id);
	public abstract Comentarios findById(int id);

}
