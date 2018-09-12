package br.com.sis.pedidos.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String senha;

    public CredenciaisDTO(){}

}
