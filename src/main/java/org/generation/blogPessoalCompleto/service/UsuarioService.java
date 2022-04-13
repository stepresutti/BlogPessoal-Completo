package org.generation.blogPessoalCompleto.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoalCompleto.model.Usuario;
import org.generation.blogPessoalCompleto.model.UsuarioLogin;
import org.generation.blogPessoalCompleto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario>cadastrarUsuario (Usuario usuario){
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
		return Optional.empty();
		
		}
			
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		return Optional.of(usuarioRepository.save(usuario));
		
	}
	
		public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
			//verificando se existe esse usuario e senha
			Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
			 

			if(usuario.isPresent()) {
				if(compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(geradorBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
			
				//logou
				return usuarioLogin;
			}
		}
			return Optional.empty();

	}
		
		private boolean compararSenhas(String senhaDigitada, String senhaDoBanco) {
		//aqui usamos boolean pq ou ba senha bate com o banco de dados ou não
			 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			 return encoder.matches(senhaDigitada, senhaDoBanco); 
			 //compara senha digitada com a do banco de dados
		}
		
		/**
		 * aqui todo o preparo p/ criptografar que fizemos no security vai se consumar de fato
		 * o BCrypt criptografa sozinho não precisa ser feito manualmente depois
		 */
		private String criptografarSenha(String senha) {
			 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			 
			 return encoder.encode(senha); //devolve senha criptografada 
					
	}
		
		private String geradorBasicToken(String usuario, String senha) {
			
			String token = usuario + ":" + senha;
			
			byte[] tokenBase64 = Base64. encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
			//faz o import
			//tokenBase64 é a tecnologia que usamos para fazer o token
			return "Basic " + new String(tokenBase64);
			
		}


	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		return null;
	}
}
