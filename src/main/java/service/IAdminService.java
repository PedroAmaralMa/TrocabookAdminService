package service;

import DTO.UsuarioDTO;
import response.AdminResponse;

import java.util.List;

public interface IAdminService {
    AdminResponse cadastro(String nome, String senha, String email);

    AdminResponse loginAdmin(String email, String senha);

    AdminResponse obterDashboard();

    AdminResponse listarUsuarios();

    AdminResponse excluirUsuario(int id);

    AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO);
}
