package com.lab.labweb.config;

import com.lab.labweb.DTO.DashboardDTO;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Componente responsável por inicializar dados no banco ao iniciar a aplicação.
 *
 * <p>Esta classe implementa {@link CommandLineRunner}, executando a inserção de
 * dados de dashboard assim que a aplicação é iniciada.</p>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>O atributo {@link #api} do tipo {@link PrincipalApiClient} é atualmente
 *       utilizado como mock para gerar dados de teste.</li>
 *   <li>No futuro, o {@link PrincipalApiClient} será substituído pelo
 *       {@link IPrincipalApiClient} (interface FeignClient), e este mock
 *       não será mais utilizado.</li>
 *   <li>Os dashboards criados aqui são apenas para testes de prototipagem.</li>
 * </ul>
 *
 * <p><b>Funcionamento:</b></p>
 * <ol>
 *   <li>Obtém os dados do dashboard usando o {@link PrincipalApiClient}.</li>
 *   <li>Cria um novo {@link Dashboard} com os valores obtidos.</li>
 *   <li>Define a data de criação como a data atual ou uma data fixa (para testes).</li>
 *   <li>Salva os dashboards no {@link DashboardRepository}.</li>
 * </ol>
 */
@Component
public class InicializacaoDados implements CommandLineRunner {

    /**
     * Cliente API utilizado atualmente apenas como mock.
     * <p>No futuro, será substituído pelo {@link IPrincipalApiClient}.</p>
     */
    @Autowired
    private PrincipalApiClient api;

    /**
     * Repositório de dashboards para salvar os dados inicializados.
     */
    @Autowired
    private DashboardRepository dashboardRepository;

    /**
     * Executa a inicialização de dados no banco.
     *
     * @param args Argumentos passados na inicialização (não utilizados)
     * @throws Exception Caso ocorra algum erro durante a inserção dos dados
     */
    @Override
    public void run(String... args) throws Exception {
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
