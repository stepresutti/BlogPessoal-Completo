package org.generation.blogPessoalCompleto.repository;

import org.generation.blogPessoalCompleto.model.Usuario;
import org.generation.blogPessoalCompleto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){

        usuarioRepository.save(new Usuario(0L, "DJ Cleiton", "cleitinho@pedra.com", "cebeçadegelo","https://i1.sndcdn.com/artworks-000117495464-9xb0g7-t500x500.jpg"));
        usuarioRepository.save(new Usuario(0L, "DJ Laurinha lero", "Laura@lero.com", "laura123","https://cdn.gamarevista.com.br/2020/07/LaurinhaLero2-1024x759.jpg"));
        usuarioRepository.save(new Usuario(0L, "Ednaldo Pereira", "edinho@pereira.com", "valenada324","https://pbs.twimg.com/profile_images/1293925081542995971/s2la3KS9_400x400.png"));
        usuarioRepository.save(new Usuario(0L, "MC Naninha", "naninha@imperiobronze.com", "trabalholindo","https://images.genius.com/badbd24d146c261d155085c30ef6c067.726x726x1.jpg"));

    }

    @Test
    @DisplayName("Retorna apenas um usuario")
    public void deveRetornaUmUsuario(){

        Optional<Usuario> usuario = usuarioRepository.findByUsuario("naninha@imperiobronze.com");
        assertTrue(usuario.get().getUsuario().equals("naninha@imperiobronze.com"));
    }

    @Test
    @DisplayName("Retorna dois usuarios")
    public void deveRetornarDoisUsuarios(){
        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("DJ");
        assertEquals(2, listaDeUsuarios.size());

        assertTrue(listaDeUsuarios.get(0).getNome().equals("DJ Cleiton"));
        assertTrue(listaDeUsuarios.get(1).getNome().equals("DJ Laurinha lero"));


    }

}
