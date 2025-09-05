package com.lab.labweb.service;

import com.lab.labweb.DTO.UsuarioDTO;
import com.lab.labweb.response.AdminResponse;

import java.util.List;

public interface IAdminService {
    AdminResponse cadastro(String nome, String senha, String email);

    AdminResponse loginAdmin(String email, String senha);

    AdminResponse obterDashboard();

    AdminResponse obterUsuario(int id);

    AdminResponse obterUsuario(int id, List<UsuarioDTO> lista);

    AdminResponse listarUsuarios();

    AdminResponse excluirUsuario(int id);

    AdminResponse excluirUsuario(int id, List<UsuarioDTO> lista);

    AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO);

    AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO, List<UsuarioDTO> lista);
}
