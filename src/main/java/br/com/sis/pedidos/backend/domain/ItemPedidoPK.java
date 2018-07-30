package br.com.sis.pedidos.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return Objects.equals(pedido, that.pedido) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}
