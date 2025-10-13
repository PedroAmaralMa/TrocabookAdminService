package com.lab.labweb.model.DTO;

/**
 * DTO (Data Transfer Object) utilizado para transportar dados de um administrador
 * entre o front-end e o back-end, sem incluir relacionamentos pesados
 * como a lista de logs.
 *
 * <p>Este DTO é utilizado principalmente em endpoints de cadastro, login e
 * recuperação de informações básicas do administrador.</p>
 *
 * <p><b>Campos principais:</b></p>
 * <ul>
 *   <li>{@code id} – identificador único do administrador</li>
 *   <li>{@code nome} – nome completo do administrador</li>
 *   <li>{@code email} – email do administrador</li>
 *   <li>{@code senha} – senha do administrador</li>
 * </ul>
 */
public class AdminDTO {

    /** Identificador único do administrador. */
    private int id;

    /** Nome completo do administrador. */
    private String nome;

    /** Email do administrador. */
    private String email;

    /** Senha do administrador. */
    private String senha;

    /**
     * Obtém o identificador do administrador.
     *
     * @return id do administrador
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do administrador.
     *
     * @param id id do administrador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do administrador.
     *
     * @return nome do administrador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do administrador.
     *
     * @param nome nome do administrador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email do administrador.
     *
     * @return email do administrador
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do administrador.
     *
     * @param email email do administrador
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém a senha do administrador.
     *
     * @return senha do administrador
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do administrador.
     *
     * @param senha senha do administrador
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
