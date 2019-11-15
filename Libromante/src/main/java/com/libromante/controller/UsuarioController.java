package com.libromante.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libromante.entity.Usuario;
import com.libromante.service.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioserviceImpl")
	private UsuarioService usuarioServ;
	
	
	@PutMapping("/addusuario")
	public boolean añadirUsuario(@RequestBody @Valid Usuario usuario) {
		return usuarioServ.addUsuario(usuario);
	}
	
	@DeleteMapping("/usuario/{id}")
	public boolean eliminarusuario(@PathVariable("id") int id) {
		return usuarioServ.removeUsuario(id);
	}
}
