package br.com.sis.pedidos.sistemapedidos.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento {

    @Getter
    @Setter
    private Integer numeroDeParcelas;

    public PagamentoComCartao(){
        super();
    }

    public PagamentoComCartao(EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
