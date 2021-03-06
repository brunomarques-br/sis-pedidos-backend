package br.com.sis.pedidos.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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
	@Getter
	@Setter
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido() {
		super();
	}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco endereco_entrega) {
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.endereco_entrega = endereco_entrega;
	}

	public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedido ip : itens) {
			soma += ip.getSubTotal();
		}
		return soma;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido Nº: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstadoPagamento().getDescricao());
		builder.append("\nDetalhes:\n");
		for (ItemPedido ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pedido pedido = (Pedido) o;
		return Objects.equals(id, pedido.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
