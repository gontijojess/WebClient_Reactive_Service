package infnet.gontijo.projetoreactivespring.service;

import infnet.gontijo.projetoreactivespring.model.Filme;
import infnet.gontijo.projetoreactivespring.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepository;

    public Flux<Filme> findAll(){
        return filmeRepository.findAll();
    }

    public Mono<Filme> findById(Long id){
        return filmeRepository.findById(id);
    }

    public Mono<Filme> save(Filme produto) {
        return filmeRepository.save(produto);
    }

    public Mono<Void> deleteById(Long id){
        return filmeRepository.deleteById(id);
    }

    public Mono<Filme> update(Long id, Filme filmeAtualizado) {
        return filmeRepository.findById(id)
                .flatMap(filmeExistente -> {
                    filmeExistente.setNome(filmeAtualizado.getNome());
                    filmeExistente.setDiretor(filmeAtualizado.getDiretor());
                    filmeExistente.setAno(filmeAtualizado.getAno());
                    filmeExistente.setGenero(filmeAtualizado.getGenero());
                    return filmeRepository.save(filmeExistente);
                })
                .switchIfEmpty(Mono.empty());
    }


}
