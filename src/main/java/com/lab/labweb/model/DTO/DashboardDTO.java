package com.lab.labweb.model.DTO;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para transferência de dados do Dashboard.
 *
 * <p>Esta classe encapsula dados agregados do sistema, como total de usuários,
 * total de livros e total de negociações, geralmente utilizados pelo front-end
 * ou por outros serviços internos.</p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *   <li>{@code totalUsuarios} – total de usuários cadastrados no sistema</li>
 *   <li>{@code totalLivros} – total de livros disponíveis no sistema</li>
 *   <li>{@code totalNegociacao} – total de negociações realizadas</li>
 *   <li>{@code dataCriacao} – data de referência da contagem dos dados (para snapshots)</li>
 * </ul>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>Classe apenas para transferência de dados, sem lógica de negócio.</li>
 *   <li>Utilizada tanto na exibição do dashboard via API REST quanto em relatórios internos.</li>
 *   <li>Permite rastreamento histórico dos dados ao longo do tempo.</li>
 * </ul>
 */
public class DashboardDTO {

    /** Total de usuários cadastrados no sistema. */
    private Long totalUsuarios;

    /** Total de livros disponíveis no sistema. */
    private Long totalLivros;

    /** Total de negociações realizadas no sistema. */
    private Long totalNegociacao;

    /** Data de referência para os dados agregados (opcional para relatórios). */
    private LocalDate dataCriacao;

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
