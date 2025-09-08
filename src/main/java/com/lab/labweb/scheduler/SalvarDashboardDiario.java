package com.lab.labweb.scheduler;

import com.lab.labweb.DTO.DashboardDTO;
import com.lab.labweb.config.IPrincipalApiClient;
import com.lab.labweb.config.PrincipalApiClient;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Classe responsável por agendar a tarefa de salvar diariamente
 * os dados consolidados do {@link Dashboard} no banco de dados.
 *
 * A execução é programada para ocorrer todos os dias à meia-noite
 * através da anotação {@link Scheduled}.
 *
 * <p><b>IMPORTANTE:</b></p>
 * <ul>
 *     <li>O atributo {@link PrincipalApiClient} está sendo usado
 *     temporariamente apenas para <b>mock de testes</b>.</li>
 *     <li>No futuro, esse atributo será removido e substituído
 *     pelo {@link IPrincipalApiClient}, que é a interface oficial
 *     de comunicação com a API principal.</li>
 * </ul>
 *
 * Fluxo de execução:
 * <ol>
 *     <li>Obter os dados do dashboard via API mockada.</li>
 *     <li>Transformar os dados em uma entidade {@link Dashboard}.</li>
 *     <li>Salvar o registro no banco de dados.</li>
 * </ol>
 *
 *
 *
 */
@Component
public class SalvarDashboardDiario {

    /**
     * Cliente API temporário para mock de testes.
     *
     * <p><b>Será removido no futuro</b> e substituído por
     * {@link IPrincipalApiClient}.</p>
     */
    @Autowired
    private PrincipalApiClient api;

    @Autowired
    private DashboardRepository dashboardRepository;

    /**
     * Tarefa agendada para ser executada diariamente à meia-noite.
     *
     * Obtém os dados do {@link DashboardDTO}, converte para entidade
     * {@link Dashboard} e persiste no banco.
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void salvarDiario() {
        DashboardDTO dashboardDTO = api.obterDashboard();
        Dashboard dashboard = new Dashboard();

        dashboard.setTotalUsuarios(dashboardDTO.getTotalUsuarios());
        dashboard.setTotalLivros(dashboardDTO.getTotalLivros());
        dashboard.setTotalNegociacao(dashboardDTO.getTotalNegociacao());
        dashboard.setDataCriacao(LocalDate.now());

        dashboardRepository.save(dashboard);
    }
}
