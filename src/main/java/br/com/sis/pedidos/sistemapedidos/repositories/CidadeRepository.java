package br.com.sis.pedidos.sistemapedidos.repositories;

import br.com.sis.pedidos.sistemapedidos.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
