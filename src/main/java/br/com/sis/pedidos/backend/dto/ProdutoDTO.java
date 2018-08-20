package br.com.sis.pedidos.backend.dto;

import br.com.sis.pedidos.backend.domain.Produto;
import br.com.sis.pedidos.backend.services.validation.ClienteUpdate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@ClienteUpdate
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.preco = obj.getPreco();
    }

}

