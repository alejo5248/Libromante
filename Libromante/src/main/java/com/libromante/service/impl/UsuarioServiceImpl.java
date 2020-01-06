

package com.libromante.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libromante.entity.Usuario;
import com.libromante.repository.UsuarioRepository;
import com.libromante.service.UsuarioService;

@Service("usuarioserviceImpl")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
	private Log log = LogFactory.getLog(UsuarioServiceImpl.class);

	@Autowired
	@Qualifier("usuariorepository")
	private UsuarioRepository usuarioRep;
	

	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listAllUsuarios() {
		
		return usuarioRep.findAll();
	}

	@Override
	@Transactional
	public boolean addUsuario(Usuario usuario) {
		try {
			usuarioRep.save(usuario);
			return true;
		}catch(Exception e) {
			return false;
		}
	
	}

	@Override
	@Transactional
	public boolean updateUsuario(Usuario usuario) {
		try {
			usuarioRep.save(usuario);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean removeUsuario(int id) {
		try {
			Usuario usuario = findUsuarioById(id);
			if(usuario != null) {
				usuarioRep.delete(usuario);
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(int id) {
		
		return usuarioRep.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		Usuario usuario = usuarioRep.findByUsername(username);
		if(usuario ==null ) {
			log.error("Error en el login: no existe el usuario '" + username+ "' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username+ "' en el sistema!");
		}
		List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).peek(authority -> log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioRep.findByUsername(username);
	}

}

