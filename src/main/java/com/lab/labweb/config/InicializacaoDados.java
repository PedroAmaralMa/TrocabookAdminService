package com.lab.labweb.config;

import com.lab.labweb.model.DTO.DashboardDTO;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.repository.DashboardRepository;
import com.lab.labweb.service.IDadosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InicializacaoDados implements CommandLineRunner {
    private final IDadosService api;
    final DashboardRepository dashboardRepository;

    InicializacaoDados(IDadosService api, DashboardRepository dashboardRepository) {
        this.api = api;
        this.dashboardRepository = dashboardRepository;
    }


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
