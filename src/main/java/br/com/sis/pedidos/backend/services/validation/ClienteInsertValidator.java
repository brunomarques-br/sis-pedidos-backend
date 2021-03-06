package br.com.sis.pedidos.backend.services.validation;

import br.com.sis.pedidos.backend.domain.Cliente;
import br.com.sis.pedidos.backend.domain.TipoCliente;
import br.com.sis.pedidos.backend.dto.ClienteNewDTO;
import br.com.sis.pedidos.backend.repositories.ClienteRepository;
import br.com.sis.pedidos.backend.resources.exceptions.FieldMessage;
import br.com.sis.pedidos.backend.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
            list.add(new FieldMessage("cpf_cnpj", "CPF Inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpf_cnpj())) {
            list.add(new FieldMessage("cpf_cnpj", "CNPJ Inválido"));
        }

        Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
        if (cliente != null) {
            list.add(new FieldMessage("email", "E-mail já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
