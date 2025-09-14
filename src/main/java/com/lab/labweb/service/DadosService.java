package com.lab.labweb.service;


import com.lab.labweb.model.DTO.DashboardDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DadosService implements IDadosService{

    private final RestTemplate restTemplate;

    private static final String api = "http://localhost:8080/dados";

    @Autowired
    DadosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DashboardDTO obterDashboard() {
        ResponseEntity<DashboardDTO> dashboard = restTemplate.exchange(
                api + "/dashboard",
                HttpMethod.GET,
                null,
                DashboardDTO.class
                );
        return dashboard.getBody();
    }

    public UsuarioDTO atualizarUsuario(int idUsuario, UsuarioDTO usuario) {
        HttpEntity<UsuarioDTO> entity = new HttpEntity<>(usuario);
        ResponseEntity<UsuarioDTO> usuarioAtualizado = restTemplate.exchange(
                api + "/atualizar/{id}",
                HttpMethod.PUT,
                entity,
                UsuarioDTO.class,
                idUsuario
        );
        return usuarioAtualizado.getBody();
    }

    public void deletarUsuario(int idUsuario) {
        restTemplate.exchange(
                api + "/deletar/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                idUsuario
        );

    }


}
