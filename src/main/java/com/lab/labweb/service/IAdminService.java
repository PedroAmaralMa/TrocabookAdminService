package com.lab.labweb.service;

import com.lab.labweb.DTO.UsuarioDTO;
import com.lab.labweb.response.AdminResponse;

import java.util.List;

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
 * <p><b>Métodos de teste (usados apenas com o mock {@code PrincipalApiClient}):</b></p>
 * <ul>
 *   <li>{@link #obterUsuario(int, List)} → apenas teste, remover futuramente</li>
 *   <li>{@link #excluirUsuario(int, List)} → apenas teste, remover futuramente</li>
 *   <li>{@link #alterarUsuario(int, UsuarioDTO, List)} → apenas teste, remover futuramente</li>
 * </ul>
 */
public interface IAdminService {

    AdminResponse cadastro(String nome, String senha, String email);

    AdminResponse loginAdmin(String email, String senha);

    AdminResponse obterDashboard();

    AdminResponse obterUsuario(int id);

    /** @deprecated Método apenas para testes locais com lista mockada. Remover futuramente. */
    AdminResponse obterUsuario(int id, List<UsuarioDTO> lista);

    AdminResponse listarUsuarios();

    AdminResponse excluirUsuario(int id);

    /** @deprecated Método apenas para testes locais com lista mockada. Remover futuramente. */
    AdminResponse excluirUsuario(int id, List<UsuarioDTO> lista);

    AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO);

    /** @deprecated Método apenas para testes locais com lista mockada. Remover futuramente. */
    AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO, List<UsuarioDTO> lista);
}
