package com.lab.labweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LogAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Operacao operacao;

    @ManyToOne
    @JoinColumn(name = "admin_responsavel_id")
    private Admin adminResponsavel;

    @Column(nullable = false)
    private int cd_usuario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataOperacao;

    public enum Operacao {
        ALTERACAO, EXCLUSAO
    }

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
