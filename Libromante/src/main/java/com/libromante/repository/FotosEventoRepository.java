package com.libromante.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.FotosEvento;

@Repository("fotoseventorepository")
public interface FotosEventoRepository extends JpaRepository<FotosEvento, Serializable> {
	
	

}
