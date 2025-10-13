package com.lab.labweb.scheduler;

import com.lab.labweb.model.DTO.DashboardDTO;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.repository.DashboardRepository;
import com.lab.labweb.service.IDadosService;
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
 * Fluxo de execução:
 * <ol>
 *     <li>Obter os dados do dashboard via API.</li>
 *     <li>Transformar os dados em uma entidade {@link Dashboard}.</li>
 *     <li>Salvar o registro no banco de dados.</li>
 * </ol>
 *
 *
 *
 */
@Component
public class SalvarDashboardDiario {

    final DashboardRepository dashboardRepository;

    private final IDadosService api;

    @Autowired
    SalvarDashboardDiario(DashboardRepository dashboardRepository, IDadosService api) {
        this.dashboardRepository = dashboardRepository;
        this.api = api;
    }

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
