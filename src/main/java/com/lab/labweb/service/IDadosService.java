package com.lab.labweb.service;

import com.lab.labweb.model.DTO.DashboardDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;

public interface IDadosService {
    DashboardDTO obterDashboard();
    UsuarioDTO atualizarUsuario(int idUsuario, UsuarioDTO usuario);
    void deletarUsuario(int idUsuario);
}
