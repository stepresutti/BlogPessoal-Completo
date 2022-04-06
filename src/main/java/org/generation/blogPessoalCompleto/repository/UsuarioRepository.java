package org.generation.blogPessoalCompleto.repository;

import java.util.Optional;

import org.generation.blogPessoalCompleto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsuario(String usuario); 
	/**
	 * o "optional" significa que pode vir, ou não, um usuario quando alguem logar
	 * também pode vir lista de usuarios com x nome quando alguem fizer o login
	 */
}
