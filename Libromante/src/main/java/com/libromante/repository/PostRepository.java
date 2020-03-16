package com.libromante.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libromante.entity.Post;

public interface PostRepository extends JpaRepository<Post, Serializable>{
	Post save(Post e);
	public List<Post> findAll();
}
