package br.com.sis.pedidos.sistemapedidos.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    public Categoria(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) &&
                Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
