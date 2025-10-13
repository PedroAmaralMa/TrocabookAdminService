package com.lab.labweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa um registro de operação realizada por um administrador.
 *
 * <p>Esta classe mantém o histórico de ações críticas executadas por administradores,
 * permitindo rastreabilidade e auditoria do sistema.</p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *   <li>{@link #id} – Identificador único do log.</li>
 *   <li>{@link #operacao} – Tipo de operação realizada (ALTERACAO ou EXCLUSAO).</li>
 *   <li>{@link #adminResponsavel} – Administrador que executou a operação.</li>
 *   <li>{@link #cd_usuario} – Código do usuário alvo da operação.</li>
 *   <li>{@link #dataOperacao} – Data em que a operação foi realizada.</li>
 * </ul>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>O relacionamento {@link Admin} permite associar cada log ao administrador responsável.</li>
 *   <li>A data da operação é formatada como "dd/MM/yyyy" para exibição consistente em JSON.</li>
 *   <li>O enum {@link Operacao} define as operações possíveis: ALTERACAO e EXCLUSAO.</li>
 * </ul>
 */
@Entity
public class LogAdmin {

    /** Identificador único do log. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Tipo de operação realizada (ALTERACAO ou EXCLUSAO). */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Operacao operacao;

    /** Administrador responsável pela operação. */
    @ManyToOne
    @JoinColumn(name = "admin_responsavel_id")
    private Admin adminResponsavel;

    /** Código do usuário alvo da operação. */
    @Column(nullable = false)
    private int cd_usuario;

    /** Data em que a operação foi realizada. */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataOperacao;

    /**
     * Enumeração das operações possíveis em LogAdmin.
     */
    public enum Operacao {
        ALTERACAO, EXCLUSAO
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Admin getAdminResponsavel() {
        return adminResponsavel;
    }

    public void setAdminResponsavel(Admin adminResponsavel) {
        this.adminResponsavel = adminResponsavel;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public LocalDate getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(LocalDate dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
