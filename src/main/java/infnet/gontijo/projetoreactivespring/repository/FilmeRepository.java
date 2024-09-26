package infnet.gontijo.projetoreactivespring.repository;

import infnet.gontijo.projetoreactivespring.model.Filme;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface FilmeRepository extends R2dbcRepository<Filme, Long> {
}
