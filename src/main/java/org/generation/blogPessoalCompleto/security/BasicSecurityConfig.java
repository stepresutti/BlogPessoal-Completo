package org.generation.blogPessoalCompleto.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired // deixa a classe de outro pacote disponivel para usar aqui (local)
	private UserDetailsService userDetailsService;
	
	@Override // neste caso o override permite login por usuario em memoria (usaremos para teste para não ter que cadastrar um usuário)
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/**
		 *  PROTECTED: a proteção permite que só a segunça tenha acesso ao usuario em memoria 
		 *  AUTH: apelido 
		 *  THROWS (excessão): indica que se eu quiser usar um usuário do meu db além desse memoria eu também consigo 
		 */
		
		auth.userDetailsService(userDetailsService);
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(passwordEncoder().encode("root"))
		.authorities("ROLE_USER");
		
	}
		
		@Bean // faz o mesmo que o autowired só que na aplicação toda (global)
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		
		/**
		 * inMemoryAuthentication: para entrar na aplicação precisa de usuario e senha
		 * ROLE_USER: indica que acima foi feita a atribuição do usuário
		 * passwordEncoder: usamos depois no bean para encriptar a senha!!!!!!! 
		 * BCryptPasswordEncoder: encripta de fato a senha!!!!!!!!!!
		 */
	
	}
		
		@Override 
		protected void configure(HttpSecurity http) throws Exception {
			
			/**
			 * VOID É VAZIO POIS NÃO RETORNA NADA - SÓ CONFIGURAMOS ENDPOINT:S
			 * esse override serve como uma rota a mais assim o usuario pode cadastrar e logar 
			 * 
			 * authorizeRequests: serve para segurança basica 
			 * define o que ta permitido para qualquer usuario e o que é para logados/cadastrados
			*/

			http.authorizeRequests() //libera acesso as Requests
			.antMatchers("/usuarios/logar").permitAll() // independente se é cadastrado ou não ta permitido
			.antMatchers("/usuarios/cadastrar").permitAll() // independente se é cadastrado ou não ta permitido
			.antMatchers(HttpMethod.OPTIONS).permitAll() // permite resposta se a requisição deu certo ou não
			
			// daqui pra baixo toda e qualquer requisição fora as acima precisam de autenticação
			.anyRequest().authenticated() 
			.and().httpBasic() //indica que tudo vai ser feito por get/post/put/delete
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // indica que tokens serão temporários
			.and().cors() // auxiliar do crossorigins da controller
			.and().csrf().disable(); // por padrão o basic trava atualização e exclusões então essa função serve pra permitir 
			
		
	}
}
