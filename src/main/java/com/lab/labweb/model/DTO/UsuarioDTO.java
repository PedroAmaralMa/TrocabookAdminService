package com.lab.labweb.model.DTO;

/**
 * Data Transfer Object (DTO) para Usuário.
 *
 * <p>Esta classe é utilizada para transferir dados de usuários entre
 * o back-end, o front-end ou serviços, sem expor a entidade completa
 * do banco de dados.</p>
 *
 * <p><b>Atributos principais:</b></p>
 * <ul>
 *   <li>{@code id} – identificador único do usuário</li>
 *   <li>{@code avaliacao} – avaliação média do usuário</li>
 *   <li>{@code status} – status do usuário ('A' = Ativo, 'I' = Inativo)</li>
 * </ul>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>Classe apenas para transferência de dados, sem lógica de negócio.</li>
 *   <li>Facilita a comunicação entre serviços e a apresentação de informações
 *   de usuários sem expor a entidade completa.</li>
 * </ul>
 */
public class UsuarioDTO {

    /** Identificador único do usuário. */
    private int id;

    /** Avaliação média do usuário. */
    private double avaliacao;

    /** Status do usuário ('A' = Ativo, 'I' = Inativo). */
    private char status;

    /**
     * Obtém o identificador do usuário.
     *
     * @return ID do usuário
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do usuário.
     *
     * @param id ID do usuário
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém a avaliação média do usuário.
     *
     * @return avaliação média
     */
    public double getAvaliacao() {
        return avaliacao;
    }

    /**
     * Define a avaliação média do usuário.
     *
     * @param avaliacao avaliação média
     */
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * Obtém o status do usuário.
     *
     * @return status ('A' = Ativo, 'I' = Inativo)
     */
    public char getStatus() {
        return status;
    }

    /**
     * Define o status do usuário.
     *
     * @param status status do usuário ('A' = Ativo, 'I' = Inativo)
     */
    public void setStatus(char status) {
        this.status = status;
    }
}
