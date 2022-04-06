package org.generation.blogPessoalCompleto.model;

public class UsuarioLogin {
	
	/**
	 * A Classe UsuarioLogin não terá nenhuma annotation porque ela
	 * não irá gerar uma tabela no Banco de Dados.
	 * 
	 * A principal função desta classe é servir de apoio ao processo
	 * de login na api.
	 * 
	 */
	
	private Long id;

	private String nome;

	private String usuario;

	private String senha;

	private String foto;

	private String token; // um token é gerado a cada login p/ verificar acesso

	
	/**
	 * getters e setters são utilizados 
	 * nesse caso para checar no banco de dados os dados de login
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
