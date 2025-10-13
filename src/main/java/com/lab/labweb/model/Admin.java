package com.lab.labweb.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidade que representa um administrador do sistema.
 *
 * <p>Esta classe mapeia a tabela {@code admins} no banco de dados e
 * contém informações essenciais para autenticação e auditoria de ações
 * administrativas.</p>
 *
 * <p><b>Requisitos atendidos:</b></p>
 * <ul>
 *   <li>"Administrador pode se cadastrar"</li>
 *   <li>"Administrador pode logar"</li>
 * </ul>
 *
 * <p><b>Observações de segurança:</b></p>
 * <ul>
 *   <li>A senha está sendo armazenada em texto puro. Futuramente, deve ser
 *       substituída por um hash seguro, como BCrypt.</li>
 *   <li>Relacionamento {@link LogAdmin} registra as operações realizadas
 *       pelo administrador, permitindo rastreabilidade.</li>
 * </ul>
 */
@Entity
@Table(name = "admins")
public class Admin {

    /** Identificador único do administrador. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nome completo do administrador. */
    @Column(nullable = false)
    private String nome;

    /** Email do administrador (único no sistema). */
    @Column(nullable = false, unique = true)
    private String email;

    /** Senha do administrador (armazenada atualmente em texto puro). */
    @Column(nullable = false)
    private String senha;

    /** Lista de logs de operações realizadas pelo administrador. */
    @OneToMany(mappedBy = "adminResponsavel", cascade = CascadeType.ALL)
    private List<LogAdmin> logAdmins;

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
