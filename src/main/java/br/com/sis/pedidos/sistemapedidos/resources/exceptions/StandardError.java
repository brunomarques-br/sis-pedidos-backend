package br.com.sis.pedidos.sistemapedidos.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private Long timestamp;

    public StandardError(Integer status, String msg, Long timestamp) {
        this.status = status;
        this.msg = msg;
        this.timestamp = timestamp;
    }

}
