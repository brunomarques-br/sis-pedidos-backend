package br.com.sis.pedidos.sistemapedidos.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    private Date instante;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private Endereco endereco_entrega;

    public Pedido() {
        super();
    }

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco endereco_entrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.endereco_entrega = endereco_entrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
