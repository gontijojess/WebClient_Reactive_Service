package infnet.gontijo.projetoreactivespring.controller;

import infnet.gontijo.projetoreactivespring.model.Filme;
import infnet.gontijo.projetoreactivespring.repository.FilmeRepository;
import infnet.gontijo.projetoreactivespring.service.FilmeWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public Flux<Filme> getAll() {
        return filmeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Filme> findById(@PathVariable Long id) {
        return filmeRepository.findById(id);
    }

    @PostMapping
    public Mono<Filme> save(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    @PutMapping("/{id}")
    public Mono<Filme> update(@PathVariable Long id, @RequestBody Filme filmeAtualizada) {
        filmeAtualizada.setId(id);
        return filmeRepository.save(filmeAtualizada);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return filmeRepository.deleteById(id);
    }
}
