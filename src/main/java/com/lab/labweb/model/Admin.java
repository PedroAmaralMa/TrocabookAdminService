package com.lab.labweb.model;

import jakarta.persistence.*;

/**
 * Entidade que representa o administrador do sistema.
 *
 * <p><b>Requisitos atendidos:</b></p>
 * <ul>
 *   <li>"Administrador pode se cadastrar"</li>
 *   <li>"Administrador pode logar"</li>
 * </ul>
 *
 * <p>Observação: A senha está sendo armazenada em texto puro.
 * Futuramente deve ser substituída por um hash seguro (ex.: BCrypt).</p>
 */
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String email;

    private String senha;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
