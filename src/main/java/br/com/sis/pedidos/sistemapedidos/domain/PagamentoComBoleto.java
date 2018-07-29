package br.com.sis.pedidos.sistemapedidos.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {

    @Getter
    @Setter
    private Date dataVencimento;
    @Getter
    @Setter
    private Date dataPagamento;

    public PagamentoComBoleto() {
        super();
    }

    public PagamentoComBoleto(EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
