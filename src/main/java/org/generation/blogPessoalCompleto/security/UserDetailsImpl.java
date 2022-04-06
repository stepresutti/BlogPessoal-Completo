/**
 * ESSA CLASSE IMPLEMENTA O USUARIO
 */

package org.generation.blogPessoalCompleto.security;

import java.util.Collection;

import org.generation.blogPessoalCompleto.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String passWord;
	
	/**
	 * toda vez que usarem username e password a UserDetailsImpl vai no usuario
	 * buscar esses valores e atribuir o usuario e senha correspondente
	 */
	public UserDetailsImpl(Usuario usuario) {
		
		this.userName = usuario.getUsuario();
		this.passWord = usuario.getSenha();
	}
	
	//para poder testar mais pra frente passamos aqui um construtor vazio
	public UserDetailsImpl() {}
	
	@Override // faz o basicsecurity ignorar o padrão dele de login e use essa definição abaixo
	public String getPassword() {
		return passWord;
	}
	@Override // faz o basicsecurity ignorar o padrão dele de login e use essa definição abaixo
	public String getUsername() {
		return userName;
	}
	
	/**METODOS PADRÃO BASIC SECURITY:
	 * 
	 */
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		// Metodo padrão de basic security
		
		return null;
		
		/**
		 * Criei esse metodo apartir da UsuarioController pelo metodo stub como indica acima
		 * Mesmo criando a controller ele pede pra que seja criado isso pra solucionar o erro
		 */
	}
}
