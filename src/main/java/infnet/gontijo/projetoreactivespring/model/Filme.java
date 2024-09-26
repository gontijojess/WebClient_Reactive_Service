package infnet.gontijo.projetoreactivespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Data
@Table("FILMES")
public class Filme {
    @Id
    private Long id;
    private String nome;
    private String diretor;
    private int ano;
    private String genero;

}
