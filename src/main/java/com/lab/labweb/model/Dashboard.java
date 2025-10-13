package com.lab.labweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa os dados agregados do sistema.
 *
 * <p>Esta classe armazena informações diárias sobre o número total de usuários,
 * livros e negociações realizadas no sistema, permitindo geração de dashboards
 * e análise histórica.</p>
 *
 * <p><b>Requisitos atendidos:</b></p>
 * <ul>
 *   <li>"Administrador visualiza dados agregados da plataforma"</li>
 * </ul>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>Os dados são normalmente salvos diariamente via {@code @Scheduled} para manter histórico.</li>
 *   <li>O atributo {@link #dataCriacao} permite rastrear a data de cada snapshot.</li>
 *   <li>O formato da data é definido para JSON como "dd/MM/yyyy" através de {@link JsonFormat}.</li>
 * </ul>
 */
@Entity
@Table(name = "dashboards")
public class Dashboard {

    /** Identificador único do registro de dashboard. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Total de usuários cadastrados no sistema. */
    private Long totalUsuarios;

    /** Total de livros disponíveis no sistema. */
    private Long totalLivros;

    /** Total de negociações realizadas no sistema. */
    private Long totalNegociacao;

    /** Data de criação do registro de dashboard. */
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
