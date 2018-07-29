package br.com.sis.pedidos.sistemapedidos.repositories;

import br.com.sis.pedidos.sistemapedidos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
