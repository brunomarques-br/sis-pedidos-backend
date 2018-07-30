package br.com.sis.pedidos.backend.dto;

import br.com.sis.pedidos.backend.domain.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    public CategoriaDTO() {
        super();
    }

    public CategoriaDTO(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
