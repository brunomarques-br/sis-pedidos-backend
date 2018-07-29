package br.com.sis.pedidos.sistemapedidos.repositories;

import br.com.sis.pedidos.sistemapedidos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
