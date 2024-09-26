package infnet.gontijo.projetoreactivespring;

import infnet.gontijo.projetoreactivespring.controller.FilmeController;
import infnet.gontijo.projetoreactivespring.model.Filme;
import infnet.gontijo.projetoreactivespring.repository.FilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@WebFluxTest(FilmeController.class)
public class FilmeControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private FilmeRepository filmeRepository;

    private Filme filme;

    @BeforeEach
    void setUp() {
        filme = new Filme(1L, "Serviço de entregas da Kiki", "Miyazaki", 1989, "Animação");
    }

    @Test
    void testGetAll(){
        doReturn(Flux.just(filme)).when(filmeRepository).findAll();
        webClient.get().uri("/filmes")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Filme.class)
                .hasSize(1)
                .contains(filme);

        verify(filmeRepository, times(1)).findAll();
    }

    @Test
    void testGetById(){
        doReturn(Mono.just(filme)).when(filmeRepository).findById(1L);
        webClient.get().uri("/filmes/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Filme.class)
                .isEqualTo(filme);

        verify(filmeRepository, times(1)).findById(1L);
    }

    @Test
    void testSave(){
        Filme novoFilme = new Filme(null, "Princesa Mononoke", "Miyazaki", 1997,"Animação");
        Filme filmeSalvo = new Filme(2L, "Princesa Mononoke", "Miyazaki", 1997, "Animação");

        doReturn(Mono.just(filmeSalvo)).when(filmeRepository).save(novoFilme);
        webClient.post().uri("/filmes")
                .bodyValue(novoFilme)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Filme.class)
                .isEqualTo(filmeSalvo);

        verify(filmeRepository, times(1)).save(novoFilme);
    }

    @Test
    void testeDelete(){
        doReturn(Mono.empty()).when(filmeRepository).deleteById(1L);
        webClient.delete().uri("/filmes/1")
                .exchange()
                .expectStatus().isOk();

        verify(filmeRepository, times(1)).deleteById(1L);
    }
}
