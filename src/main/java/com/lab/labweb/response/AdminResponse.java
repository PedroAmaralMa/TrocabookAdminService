package com.lab.labweb.response;

/**
 * Classe de resposta genérica para operações relacionadas ao administrador.
 *
 * <p>É utilizada para encapsular o resultado de um serviço ou operação,
 * incluindo se a ação foi bem-sucedida, uma mensagem explicativa
 * e qualquer dado retornado (opcional).</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 * AdminResponse response = new AdminResponse(true, "Operação concluída", usuarioDTO);
 * </pre>
 *
 * <p>Nota: o atributo {@code data} pode armazenar qualquer tipo de objeto,
 * como entidades, DTOs ou listas de objetos.</p>
 *
 *
 *
 */
public class AdminResponse {
    /**
     * Construtor da classe {@code AdminResponse}.
     *
     * @param resultado indica se a operação foi bem-sucedida
     * @param mensagem mensagem descritiva sobre o resultado
     * @param data dados retornados pela operação, se houver
     */
    public AdminResponse(boolean resultado, String mensagem, Object data) {
        this.resultado = resultado;
        this.mensagem = mensagem;
        this.data = data;
    }

    private boolean resultado;

    private String mensagem;

    private Object data;

    // Getters e Setters

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
