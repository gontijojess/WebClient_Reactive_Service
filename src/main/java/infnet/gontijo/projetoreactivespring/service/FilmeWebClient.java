package infnet.gontijo.projetoreactivespring.service;

import infnet.gontijo.projetoreactivespring.model.Filme;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FilmeWebClient {
    private final WebClient webClient;

    public FilmeWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8086/filmes").build();
    }

    public Flux<Filme> findAll() {
    return webClient.get()
            .retrieve()
            .bodyToFlux(Filme.class);
    }

    public Mono<Filme> getById(Long id){
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Filme.class);
    }

    public Mono<Filme> save(Filme filme) {
        return webClient.post()
                .bodyValue(filme)
                .retrieve()
                .bodyToMono(Filme.class);
    }

    public Mono<Void> deleteById(Long id){
        return webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
