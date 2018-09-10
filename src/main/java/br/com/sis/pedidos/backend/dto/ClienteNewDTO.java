package br.com.sis.pedidos.backend.dto;

import br.com.sis.pedidos.backend.domain.TipoCliente;
import br.com.sis.pedidos.backend.services.validation.ClienteInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteInsert
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    @Length(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
    private String nome;
    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    @Email(message = "Informe um e-mail válido")
    private String email;
    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    private String cpf_cnpj;
    @Getter
    @Setter
    private Integer tipo;
    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    private String senha;
    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    private String logradouro;
    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    private String numero;
    @Getter
    @Setter
    private String complemento;
    @Getter
    @Setter
    private String bairro;
    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    private String cep;
    @Getter
    @Setter
    private Integer cidadeId;
    @Getter
    @Setter
    @NotEmpty(message = "Campo de preenchimento obrigatório")
    private String telefone1;
    @Getter
    @Setter
    private String telefone2;
    @Getter
    @Setter
    private String telefone3;

}
