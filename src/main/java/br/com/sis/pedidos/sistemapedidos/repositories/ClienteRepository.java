package br.com.sis.pedidos.sistemapedidos.repositories;

import br.com.sis.pedidos.sistemapedidos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
