package br.com.sis.pedidos.backend.services;

import br.com.sis.pedidos.backend.domain.Categoria;
import br.com.sis.pedidos.backend.exceptions.DataIntegrityException;
import br.com.sis.pedidos.backend.exceptions.ObjectNotFoundException;
import br.com.sis.pedidos.backend.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        List<Categoria> obj = repo.findAll();
        return obj;
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possua produtos vinculados.");
        }
    }

}
