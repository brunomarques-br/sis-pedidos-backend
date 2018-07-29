package br.com.sis.pedidos.sistemapedidos.domain;

import lombok.Getter;
import lombok.Setter;

public enum EstadoPagamento {

    PENDENTE(1, "Pessoa Física"),
    QUITADO(2, "Pessoa Jurídica"),
    CANCELADO(3, "Cancelado");

    @Getter
    @Setter
    private int cod;
    @Getter
    @Setter
    private String descricao;

    private EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null)
            return null;
        for (EstadoPagamento x : EstadoPagamento.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
