package org.generation.blogPessoalCompleto.controller;


import org.generation.blogPessoalCompleto.model.Usuario;
import org.generation.blogPessoalCompleto.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

    @Autowired
    private UsuarioService usuarioService;

    //somente no teste de controlller, pois usamos o padrão rest que utiliza dos verbos e metodos HTTP -> GET-POST-PUT-DELETE
    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    @Order(1)
    @DisplayName("Cadastrar apenas um usuário")
    public void deveCadastrarUmUsuario() {

        HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, "Jose", "jose@imperiobronze.com", "trabalholindo", "https://i.imgur.com/FETvs2O.jpg"));
        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
        assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
    }

    @Test
    @Order(2)
    @DisplayName("Alterar um Usuário")
    public void deveAlterarUmUsuario() {

        Optional<Usuario> usuarioCreate = usuarioService
                .cadastrarUsuario(new Usuario(0L, "Victor Reis", "victor@teste.com.br", "123456", ""));

        Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(), "Victor Martins dos Reis", "victor_reis@teste.com.br", "121314", "http://google.com.br");

        HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);

        ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("root", "root")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, Usuario.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
        assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario());
        assertEquals(usuarioUpdate.getFoto(), resposta.getBody().getFoto());


    }
}