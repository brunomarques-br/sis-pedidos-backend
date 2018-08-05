package br.com.sis.pedidos.backend.services;

import br.com.sis.pedidos.backend.domain.Cidade;
import br.com.sis.pedidos.backend.domain.Cliente;
import br.com.sis.pedidos.backend.domain.Endereco;
import br.com.sis.pedidos.backend.domain.TipoCliente;
import br.com.sis.pedidos.backend.dto.ClienteDTO;
import br.com.sis.pedidos.backend.dto.ClienteNewDTO;
import br.com.sis.pedidos.backend.exceptions.DataIntegrityException;
import br.com.sis.pedidos.backend.exceptions.ObjectNotFoundException;
import br.com.sis.pedidos.backend.repositories.ClienteRepository;
import br.com.sis.pedidos.backend.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        List<Cliente> obj = repo.findAll();
        return obj;
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newCliente = find(obj.getId());
        updateData(newCliente, obj);
        return repo.save(newCliente);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possua endereço e pedidos vinculados.");
        }
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO objDTO) {
        Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf_cnpj(), TipoCliente.toEnum(objDTO.getTipo().getCod()));
        Cidade cidade = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2() != null)
            cliente.getTelefones().add(objDTO.getTelefone2());
        if (objDTO.getTelefone3() != null)
            cliente.getTelefones().add(objDTO.getTelefone3());
        return cliente;
    }

    private void updateData(Cliente newCliente, Cliente obj) {
        newCliente.setNome(obj.getNome());
        newCliente.setEmail(obj.getEmail());
    }

}
