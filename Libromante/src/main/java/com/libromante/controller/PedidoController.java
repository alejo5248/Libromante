package com.libromante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.libromante.entity.Pedido;
import com.libromante.service.PedidoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	@Qualifier("pedidoserviceimpl")
	private PedidoService pedidoServ;
	
	@GetMapping("/mostrarpedidos/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Pedido mostrarFacturas(@PathVariable int id) {
		return pedidoServ.findPedidoById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public boolean eliminarFactura(@PathVariable int id) {
		return pedidoServ.deletePedidoById(id);
	}
	
	@PutMapping("/addpedido")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pedido crear(@RequestBody Pedido pedido) {
		return pedidoServ.savePedido(pedido);
	}
	

}
