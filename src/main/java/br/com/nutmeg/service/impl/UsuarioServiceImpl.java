package br.com.nutmeg.service.impl;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nutmeg.model.Usuario;
import br.com.nutmeg.respository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	public Usuario save(Usuario usuario) {
		return usuarioRepository.saveAndFlush(usuario);
	}

	public boolean usuarioExiste(Usuario usuario) {
		return (usuarioRepository.findByUsername(usuario.getUsername()) == null ? false : true);
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
    }



}
