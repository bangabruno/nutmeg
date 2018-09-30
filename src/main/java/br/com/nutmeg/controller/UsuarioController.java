package br.com.nutmeg.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutmeg.enums.MessageError;
import br.com.nutmeg.model.ResponseError;
import br.com.nutmeg.model.Usuario;
import br.com.nutmeg.respository.UsuarioRepository;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody Usuario usuario) {

		URI uri = null;
		
		if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
			return ResponseEntity.badRequest().body(new ResponseError(HttpStatus.BAD_REQUEST, MessageError.USUARIO_DUPLICADO));
		} else {
			usuario.setDtCriacao(new Date());
			usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		}
		
		Usuario novoUsuario = usuarioRepository.save(usuario);
		
		try {
			uri = new URI("/v1/usuarios/" + novoUsuario.getId());
		} catch (URISyntaxException e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, MessageError.USUARIO_FALHA_INESPERADA));
		}
		
		return ResponseEntity.created(uri).body(novoUsuario);
	}
}
