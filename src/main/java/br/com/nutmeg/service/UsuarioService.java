package br.com.nutmeg.service;

import org.springframework.stereotype.Service;

import br.com.nutmeg.model.Usuario;

@Service
public interface UsuarioService {
	
	Usuario findByUsername(String login);
	
	Usuario save(Usuario usuario);
	
	boolean usuarioExiste(Usuario usuario);
}
