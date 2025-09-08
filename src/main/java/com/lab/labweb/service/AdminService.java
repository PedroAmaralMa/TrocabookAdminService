package com.lab.labweb.service;

import com.lab.labweb.DTO.UsuarioDTO;
import com.lab.labweb.model.Admin;
import com.lab.labweb.repository.AdminRepository;
import com.lab.labweb.repository.DashboardRepository;
import com.lab.labweb.response.AdminResponse;
import com.lab.labweb.config.PrincipalApiClient; // usado como mock para testes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação concreta dos serviços administrativos.
 *
 * <p>Esta classe conecta-se tanto ao repositório local (admins cadastrados)
 * quanto ao mock {@link PrincipalApiClient}, que futuramente será substituído
 * pelo {@code IPrincipalApiClient} via Feign para comunicação com a API principal.</p>
 */
@Service
public class AdminService implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private PrincipalApiClient api; // MOCK - será substituído pelo FeignClient

    /**
     * Requisito: "Administrador pode se cadastrar".
     */
    @Override
    public AdminResponse cadastro(String nome, String senha, String email) {
        try {
            Admin admin = new Admin();
            admin.setNome(nome);
            admin.setSenha(senha); // ⚠️ armazenar senha em texto puro — melhorar com BCrypt
            admin.setEmail(email);
            adminRepository.save(admin);
            return new AdminResponse(true, "Admin cadastrado com sucesso", admin);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro no cadastro: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode logar".
     */
    @Override
    public AdminResponse loginAdmin(String email, String senha) {
        try {
            Admin admin = adminRepository.findByEmailAndSenha(email, senha);
            if (admin != null) {
                return new AdminResponse(true, "Login realizado com sucesso", admin);
            }
            return new AdminResponse(false, "Credenciais inválidas", null);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro no login: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador visualiza dados agregados da plataforma".
     */
    @Override
    public AdminResponse obterDashboard() {
        try {
            List<?> listaDashboard = dashboardRepository.findAll();
            return new AdminResponse(true, "Dashboard obtido", listaDashboard);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao obter dashboard: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode consultar um usuário específico".
     */
    @Override
    public AdminResponse obterUsuario(int id) {
        try {
            UsuarioDTO usuario = api.obterUsuario(id);
            return new AdminResponse(true, "Usuário encontrado", usuario);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao obter usuário: " + e.getMessage(), null);
        }
    }

    /**
     * Apenas para testes com lista mockada.
     */
    @Override
    public AdminResponse obterUsuario(int id, List<UsuarioDTO> lista) {
        return new AdminResponse(true, "Mock de usuário obtido", lista);
    }

    /**
     * Requisito: "Administrador gerencia usuários".
     */
    @Override
    public AdminResponse listarUsuarios() {
        try {
            List<UsuarioDTO> usuarios = api.listarUsuarios();
            return new AdminResponse(true, "Lista de usuários obtida", usuarios);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao listar usuários: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode excluir usuários".
     */
    @Override
    public AdminResponse excluirUsuario(int id) {
        try {
            api.deletarUsuario(id);
            return new AdminResponse(true, "Usuário excluído com sucesso", null);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao excluir usuário: " + e.getMessage(), null);
        }
    }

    /**
     * Apenas para testes com lista mockada.
     */
    @Override
    public AdminResponse excluirUsuario(int id, List<UsuarioDTO> lista) {
        lista.removeIf(u -> u.getId() == id);
        return new AdminResponse(true, "Usuário removido da lista mockada", lista);
    }

    /**
     * Requisito: "Administrador pode alterar dados de usuários".
     */
    @Override
    public AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioAtualizado = api.atualizarUsuario(id, usuarioDTO);
            return new AdminResponse(true, "Usuário atualizado", usuarioAtualizado);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao alterar usuário: " + e.getMessage(), null);
        }
    }

    /**
     * Apenas para testes com lista mockada.
     */
    @Override
    public AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO, List<UsuarioDTO> lista) {
        return new AdminResponse(true, "Usuário alterado na lista mockada", usuarioDTO);
    }
}
