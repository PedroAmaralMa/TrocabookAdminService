package com.lab.labweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa os dados agregados do sistema
 * (número de usuários, livros e negociações).
 *
 * <p><b>Requisitos atendidos:</b></p>
 * <ul>
 *   <li>"Administrador visualiza dados agregados da plataforma"</li>
 * </ul>
 *
 * <p>Os dados são salvos diariamente por um {@code @Scheduled}
 * para manter o histórico.</p>
 */
@Entity
@Table(name = "dashboards")
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalUsuarios;

    private Long totalLivros;

    private Long totalNegociacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public Long getTotalLivros() {
        return totalLivros;
    }

    public void setTotalLivros(Long totalLivros) {
        this.totalLivros = totalLivros;
    }

    public Long getTotalNegociacao() {
        return totalNegociacao;
    }

    public void setTotalNegociacao(Long totalNegociacao) {
        this.totalNegociacao = totalNegociacao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
