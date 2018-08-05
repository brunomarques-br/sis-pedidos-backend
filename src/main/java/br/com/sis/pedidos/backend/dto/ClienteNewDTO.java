package br.com.sis.pedidos.backend.dto;

import br.com.sis.pedidos.backend.domain.TipoCliente;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String cpf_cnpj;
    private Integer tipo;
    @Getter
    @Setter
    private String logradouro;
    @Getter
    @Setter
    private String numero;
    @Getter
    @Setter
    private String complemento;
    @Getter
    @Setter
    private String bairro;
    @Getter
    @Setter
    private String cep;
    @Getter
    @Setter
    private Integer cidadeId;
    @Getter
    @Setter
    private String telefone1;
    @Getter
    @Setter
    private String telefone2;
    @Getter
    @Setter
    private String telefone3;

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

}
