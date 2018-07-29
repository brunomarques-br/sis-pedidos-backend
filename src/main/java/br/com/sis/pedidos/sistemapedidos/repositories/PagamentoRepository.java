package br.com.sis.pedidos.sistemapedidos.repositories;

import br.com.sis.pedidos.sistemapedidos.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
