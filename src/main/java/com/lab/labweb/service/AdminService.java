package com.lab.labweb.service;

import com.lab.labweb.model.DTO.AdminDTO;
import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import com.lab.labweb.model.Admin;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.model.LogAdmin;
import com.lab.labweb.repository.AdminRepository;
import com.lab.labweb.repository.DashboardRepository;
import com.lab.labweb.repository.LogAdminRepository;
import com.lab.labweb.response.AdminResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementação concreta dos serviços administrativos.
 *
 */
@Service
public class AdminService implements IAdminService {

    final AdminRepository adminRepository;

    final DashboardRepository dashboardRepository;

    final LogAdminRepository logAdminRepository;

    AdminService(AdminRepository adminRepository, DashboardRepository dashboardRepository, LogAdminRepository logAdminRepository) {
        this.adminRepository = adminRepository;
        this.dashboardRepository = dashboardRepository;
        this.logAdminRepository = logAdminRepository;
    }
    /**
     * Requisito: "Administrador pode se cadastrar".
     */
    @Override
    public AdminResponse cadastro(AdminDTO adminDto) {
        try {
            Admin admin = new Admin();
            admin.setNome(adminDto.getNome());
            admin.setSenha(adminDto.getSenha());
            admin.setEmail(adminDto.getEmail());
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

    @Override
    public Admin obterAdminPorId(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));
    }

    /**
     * Requisito: "Administrador visualiza dados agregados da plataforma".
     */
    @Override
    public AdminResponse obterDashboard() {
        try {
            List<Dashboard> listaDashboard = dashboardRepository.findAll();
            return new AdminResponse(true, "Dashboard obtido", listaDashboard);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao obter dashboard: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode excluir usuários".
     */
    @Override
    public AdminResponse excluirUsuario(LogAdminDTO logAdmin) {
        try {
            api.deletarUsuario(logAdmin.getCdUsuario());
            salvarOperacao(logAdmin);
            return new AdminResponse(true, "Usuário excluído com sucesso", null);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao excluir usuário: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode alterar dados de usuários".
     */
    @Override
    public AdminResponse alterarUsuario(LogAdminDTO loginAdmin, int idUsuario, UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioAtualizado = api.atualizarUsuario(idUsuario, usuarioDTO);
            salvarOperacao(loginAdmin);
            return new AdminResponse(true, "Usuário atualizado", usuarioAtualizado);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao alterar usuário: " + e.getMessage(), null);
        }
    }


    public void salvarOperacao(LogAdminDTO logAdmin){
        LogAdmin novoLogAdmin = new LogAdmin();
        novoLogAdmin.setOperacao(LogAdmin.Operacao.valueOf(logAdmin.getOperacao()));
        novoLogAdmin.setAdminResponsavel(obterAdminPorId(logAdmin.getAdminResponsavelId()));
        novoLogAdmin.setCd_usuario(logAdmin.getCdUsuario());
        novoLogAdmin.setDataOperacao(LocalDate.now());
        logAdminRepository.save(novoLogAdmin);
    }
}
