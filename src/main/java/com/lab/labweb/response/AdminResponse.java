package com.lab.labweb.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {
    private boolean resultado;
    private String mensagem;
    private Object data;

    public AdminResponse(boolean resultado, String mensagem, Object data) {
        this.resultado = resultado;
        this.mensagem = mensagem;
        this.data = data;
    }
}
