package br.com.sis.pedidos.sistemapedidos.services;

import br.com.sis.pedidos.sistemapedidos.domain.Produto;
import br.com.sis.pedidos.sistemapedidos.exceptions.ObjectNotFoundException;
import br.com.sis.pedidos.sistemapedidos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Produto buscar(Integer id) {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id: " + id + ", tipo: " + Produto.class.getName()));
    }

}
