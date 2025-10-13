package com.lab.labweb.config;

import com.lab.labweb.model.DTO.DashboardDTO;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.repository.DashboardRepository;
import com.lab.labweb.service.IDadosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Componente responsável por inicializar dados no banco de dados ao iniciar a aplicação.
 *
 * <p>Este runner verifica se a tabela de dashboards está vazia e,
 * caso esteja, busca dados agregados da API externa através de {@link IDadosService}
 * e salva registros iniciais no banco de dados.</p>
 *
 * <p>Também insere um registro adicional com data fixa (01/01/2024)
 * para fins de teste ou histórico inicial.</p>
 *
 * <p>Observações:</p>
 * <ul>
 *     <li>Executa apenas se não houver dashboards cadastrados (dashboardRepository.count() == 0).</li>
 *     <li>Utiliza {@link DashboardDTO} como DTO intermediário para transferência de dados da API.</li>
 *     <li>Persiste os dados na entidade {@link Dashboard} usando {@link DashboardRepository}.</li>
 * </ul>
 */
@Component
public class InicializacaoDados implements CommandLineRunner {

    /** Serviço para consumir dados da API externa. */
    private final IDadosService api;

    /** Repositório para manipulação de dashboards no banco de dados. */
    final DashboardRepository dashboardRepository;

    /**
     * Construtor que injeta dependências.
     *
     * @param api Serviço de consumo de dados externos
     * @param dashboardRepository Repositório para persistência de dashboards
     */
    public InicializacaoDados(IDadosService api, DashboardRepository dashboardRepository) {
        this.api = api;
        this.dashboardRepository = dashboardRepository;
    }

    /**
     * Método executado na inicialização da aplicação.
     *
     * <p>Verifica se não existem dashboards cadastrados e, se necessário,
     * cria dois registros iniciais com os dados obtidos da API.</p>
     *
     * @param args Argumentos passados na execução da aplicação
     * @throws Exception Caso haja falha ao consumir a API ou salvar os dados
     */
    @Override
    public void run(String... args) throws Exception {
        if (dashboardRepository.count() == 0) {
            DashboardDTO dashboardDTO = api.obterDashboard();

            Dashboard dashboard = new Dashboard();
            dashboard.setTotalUsuarios(dashboardDTO.getTotalUsuarios());
            dashboard.setTotalLivros(dashboardDTO.getTotalLivros());
            dashboard.setTotalNegociacao(dashboardDTO.getTotalNegociacao());
            dashboard.setDataCriacao(LocalDate.now());
            dashboardRepository.save(dashboard);

            Dashboard dashboard2 = new Dashboard();
            dashboard2.setTotalUsuarios(dashboardDTO.getTotalUsuarios());
            dashboard2.setTotalLivros(dashboardDTO.getTotalLivros());
            dashboard2.setTotalNegociacao(dashboardDTO.getTotalNegociacao());
            dashboard2.setDataCriacao(LocalDate.of(2024, 1, 1));
            dashboardRepository.save(dashboard2);
        }
    }
}
