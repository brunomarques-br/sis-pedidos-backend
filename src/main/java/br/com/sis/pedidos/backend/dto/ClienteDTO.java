package br.com.sis.pedidos.backend.dto;

import br.com.sis.pedidos.backend.domain.Cliente;
import br.com.sis.pedidos.backend.services.validation.ClienteUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @NotEmpty(message = "O campo nome não pode ser vazio")
    @Length(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
    private String nome;
    @Getter
    @Setter
    @NotEmpty
    @Email(message = "Informe um e-mail válido")
    private String email;

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }
}
