package br.com.sis.pedidos.sistemapedidos.services;

import br.com.sis.pedidos.sistemapedidos.domain.Pedido;
import br.com.sis.pedidos.sistemapedidos.domain.Produto;
import br.com.sis.pedidos.sistemapedidos.exceptions.ObjectNotFoundException;
import br.com.sis.pedidos.sistemapedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id: " + id + ", tipo: " + Pedido.class.getName()));
    }

}
