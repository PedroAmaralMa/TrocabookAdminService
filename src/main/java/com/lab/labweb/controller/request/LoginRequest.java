package com.lab.labweb.controller.request;

/**
 * Utilizado para encapsular os dados
 * necessários para o login de um administrador.
 *
 * <p>Este objeto é enviado pelo cliente (front-end ou API externa)
 * no corpo da requisição ao endpoint de login, contendo as credenciais
 * do administrador.</p>
 *
 * <p><b>Campos principais:</b></p>
 * <ul>
 *   <li>{@code email} – email do administrador</li>
 *   <li>{@code senha} – senha do administrador</li>
 * </ul>
 *
 * <p>É utilizado principalmente pelo {@link com.lab.labweb.controller.AdminController}
 * nos métodos de login via API REST.</p>
 */
public class LoginRequest {

    /** Email do administrador que será utilizado para autenticação. */
    private String email;

    /** Senha do administrador que será utilizada para autenticação. */
    private String senha;

    /**
     * Obtém o email do administrador.
     *
     * @return o email do administrador
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
     * @return a senha do administrador
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
