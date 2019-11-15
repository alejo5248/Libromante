package com.libromante.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libromante.entity.Pedido;

@Repository("pedidorepository")
public interface PedidoRepository extends JpaRepository<Pedido, Serializable> {
	

}
