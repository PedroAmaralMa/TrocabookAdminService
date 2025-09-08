package com.lab.labweb.DTO;

import lombok.Data;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para o Dashboard.
 *
 * <p>Essa classe é utilizada para transferir dados agregados do sistema,
 * como total de usuários, total de livros e total de negociações,
 * geralmente entre o back-end e o front-end ou entre serviços.</p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *   <li>{@link #totalUsuarios} - Total de usuários cadastrados no sistema.</li>
 *   <li>{@link #totalLivros} - Total de livros disponíveis no sistema.</li>
 *   <li>{@link #totalNegociacao} - Total de negociações realizadas.</li>
 *   <li>{@link #dataCriacao} - Data de referência da contagem dos dados.</li>
 * </ul>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>Classe apenas para transferência de dados, não possui lógica de negócio.</li>
 *   <li>Utilizada tanto para a inicialização de dados no banco quanto para
 *   exibição no dashboard (via HTML ou API REST).</li>
 *   <li>No futuro, a data de criação será importante para snapshots históricos
 *   do dashboard.</li>
 * </ul>
 */
@Data
public class DashboardDTO {
    private Long totalUsuarios;
    private Long totalLivros;
    private Long totalNegociacao;
    private LocalDate dataCriacao;
}
