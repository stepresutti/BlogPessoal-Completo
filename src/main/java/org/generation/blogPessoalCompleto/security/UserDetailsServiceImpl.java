package org.generation.blogPessoalCompleto.security;

import java.util.Optional;

import org.generation.blogPessoalCompleto.model.Usuario;
import org.generation.blogPessoalCompleto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
/**
 * SERVICE: muito parecido com a função do repository 
 * só não fizemos lá para criar maior segurança na aplicação
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	//este override faz a trativa de erro caso o usuário não seja encontrado
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName); //se achar
		
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found.")); //se não achar

		return usuario.map(UserDetailsImpl::new).get(); 
		//metodo que vai retornar o(s) usuario(s) encontrado(s) //
		// dois pontos "::" determina atribuição //
	}
}
