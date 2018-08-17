package br.com.sis.pedidos.backend.dto;

import br.com.sis.pedidos.backend.domain.Categoria;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String nome;

    public CategoriaDTO() {
        super();
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
