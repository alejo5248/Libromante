package com.libromante.service;

import com.libromante.entity.Pedido;

public interface PedidoService {
	
	public abstract Pedido findPedidoById(int id);
	public abstract Pedido savePedido(Pedido pedido);
	public abstract boolean deletePedidoById(int id);

}
