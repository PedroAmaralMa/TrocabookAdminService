package com.lab.labweb.scheduler;

import com.lab.labweb.DTO.DashboardDTO;
import com.lab.labweb.config.IPrincipalApiClient;
import com.lab.labweb.config.PrincipalApiClient;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDate;

@Component
public class salvarDashboardDiario {
    //private IPrincipalApiClient principal;
    @Autowired
    private PrincipalApiClient api;

    @Autowired
    private DashboardRepository dashboardRepository;

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
