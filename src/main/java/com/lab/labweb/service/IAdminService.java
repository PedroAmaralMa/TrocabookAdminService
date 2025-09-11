package com.lab.labweb.service;

import com.lab.labweb.model.Admin;
import com.lab.labweb.model.DTO.AdminDTO;
import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import com.lab.labweb.model.LogAdmin;
import com.lab.labweb.response.AdminResponse;

/**
 * Interface que define os serviços disponíveis para administradores.
 *
 * <p><b>Atende à tabela de requisitos:</b></p>
 * <ul>
 *   <li>{@link #cadastro(String, String, String)} → requisito: "Administrador pode se cadastrar"</li>
 *   <li>{@link #loginAdmin(String, String)} → requisito: "Administrador pode logar"</li>
 *   <li>{@link #obterDashboard()} → requisito: "Administrador visualiza dados agregados da plataforma"</li>
 *   <li>{@link #listarUsuarios()} → requisito: "Administrador gerencia usuários"</li>
 *   <li>{@link #obterUsuario(int)} → requisito: "Administrador pode consultar um usuário específico"</li>
 *   <li>{@link #excluirUsuario(int)} → requisito: "Administrador pode excluir usuários"</li>
 *   <li>{@link #alterarUsuario(int, UsuarioDTO)} → requisito: "Administrador pode alterar dados de usuários"</li>
 * </ul>
 *
 */
public interface IAdminService {

    AdminResponse cadastro(AdminDTO adminDTO);

    AdminResponse loginAdmin(String email, String senha);

    AdminResponse obterDashboard();

    AdminResponse excluirUsuario(LogAdminDTO logAdmin);

    AdminResponse alterarUsuario(LogAdminDTO logadmin, int idUsuario, UsuarioDTO usuarioDTO);

    Admin obterAdminPorId(int id);
}
