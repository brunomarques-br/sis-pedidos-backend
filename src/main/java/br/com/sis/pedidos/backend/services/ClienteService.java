package br.com.sis.pedidos.backend.services;

import br.com.sis.pedidos.backend.domain.Cliente;
import br.com.sis.pedidos.backend.exceptions.ObjectNotFoundException;
import br.com.sis.pedidos.backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

}
