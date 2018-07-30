package br.com.sis.pedidos.backend.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<FieldMessage> list = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timestamp) {
        super(status, msg, timestamp);
    }

    public void addError(String fieldMessage, String messagem) {
        list.add(new FieldMessage(fieldMessage, messagem));
    }
}
