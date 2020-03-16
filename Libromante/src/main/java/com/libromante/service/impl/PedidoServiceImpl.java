package com.libromante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Pedido;
import com.libromante.repository.PedidoRepository;
import com.libromante.service.PedidoService;

@Service("pedidoserviceimpl")
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	@Qualifier("pedidorepository")
	private PedidoRepository pedidoRep;
	
	@Override
	@Transactional(readOnly = true)
	public Pedido findPedidoById(int id) {
		
		return pedidoRep.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pedido savePedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return pedidoRep.save(pedido);
	}

	@Override
	@Transactional
	public boolean deletePedidoById(int id) {
		try {
			Pedido pedido = findPedidoById(id);
			if(pedido != null) {
				pedidoRep.delete(pedido);
			}
			return true;
			
		}catch(Exception e) {
			return false;
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pedido> findAllPedidos() {
		
		return pedidoRep.findAll();
	}
	
	

}
