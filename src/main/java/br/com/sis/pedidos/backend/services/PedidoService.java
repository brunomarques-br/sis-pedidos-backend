package br.com.sis.pedidos.backend.services;

import br.com.sis.pedidos.backend.domain.Pedido;
import br.com.sis.pedidos.backend.exceptions.ObjectNotFoundException;
import br.com.sis.pedidos.backend.repositories.PedidoRepository;
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
