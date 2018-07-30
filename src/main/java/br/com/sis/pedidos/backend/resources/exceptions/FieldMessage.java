package br.com.sis.pedidos.backend.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String fieldName;
    @Getter
    @Setter
    private String message;

    public FieldMessage(){super();}

    public FieldMessage(String fieldName, String message) {
        super();
        this.fieldName = fieldName;
        this.message = message;
    }
}
