package com.libromante.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libromante.entity.Usuario;
import com.libromante.service.EnvioEmailService;
import com.libromante.service.UsuarioService;
import com.libromante.service.impl.UsuarioServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioserviceImpl")
	private UsuarioService usuarioServ;
	
	@Autowired
	private EnvioEmailService envEmailServ;
	
	private Log log = LogFactory.getLog(UsuarioController.class);
	
	
	@PutMapping("/addusuario")
	public boolean añadirUsuario(@RequestBody @Valid Usuario usuario) {
		 usuarioServ.addUsuario(usuario);
		try {
			envEmailServ.sendEmail(usuario);
		}catch(MailException e){
			//catch error
			log.info("Error enviando el email: " + e.getMessage());
		}
		return true;
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public boolean eliminarUsuario(@PathVariable("id") int id) {
		return usuarioServ.removeUsuario(id);
	}
	
	@GetMapping("/listusuarios")
	public List<Usuario> verUsuarios(){
		return usuarioServ.listAllUsuarios();
	}
	
	@PutMapping("/actualizarusuario/{id}")
	public Usuario actualizarUsuario(@RequestBody Usuario usuario,@Valid @PathVariable(name = "id", required = true) int id) {
		Usuario update = this.usuarioServ.findUsuarioById(id);
		if(id != 0) {
			update.setNombre(usuario.getNombre());
			update.setApellido(usuario.getApellido());
			update.setDireccion(usuario.getDireccion());
			update.setPassword(usuario.getPassword());
			this.usuarioServ.updateUsuario(update);
		}
		return update;
	}
}
