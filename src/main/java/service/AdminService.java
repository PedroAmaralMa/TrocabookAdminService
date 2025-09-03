package service;

import DTO.DashboardDTO;
import DTO.UsuarioDTO;
import config.IPrincipalApiClient;
import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdminRepository;
import response.AdminResponse;

import java.util.List;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private IPrincipalApiClient api;


    public AdminResponse cadastro(String nome, String email, String senha) {
        try {
            Admin admin = new Admin();
            admin.setNome(nome);
            admin.setEmail(email);
            admin.setSenha(senha);
            Admin novoAdmin = adminRepository.save(admin);

            return new AdminResponse(true, "Cadastro realizado com sucesso", novoAdmin);

        } catch(Exception e){
            return new AdminResponse(false, "Erro no cadastro" + e.getMessage(), null);
        }

    }

    public AdminResponse loginAdmin(String email, String senha){
        try {
            Admin admin = adminRepository.findByEmailAndSenha(email, senha);
            return new AdminResponse(true, "Login realizado com sucesso", admin);
        } catch(Exception e){
            return new AdminResponse(false, "Erro no login" + e.getMessage(), null);
        }

    }

    public AdminResponse obterDashboard(){
        try {
            Long totalUsuarios = api.obterTotalUsuarios();
            Long totalLivros = api.obterTotalLivros();
            Long totalNegociacao = api.obterTotalNegociacao();

            DashboardDTO dashboard = new DashboardDTO();
            dashboard.setTotalUsuarios(totalUsuarios);
            dashboard.setTotalLivros(totalLivros);
            dashboard.setTotalNegociacao(totalNegociacao);
            return new AdminResponse(true, "Dashboard obtido", dashboard);
        } catch(Exception e){
            return new AdminResponse(false, "Erro no obter dashboard" + e.getMessage(), null);
        }
    }

    public AdminResponse listarUsuarios() {
        try {
            List<UsuarioDTO> usuarios = api.listarUsuarios();
            return new AdminResponse(true, "Lista de usuarios buscada com sucesso", usuarios);
        } catch(Exception e){
            return new AdminResponse(false, "Erro no listar usuarios" + e.getMessage(), null);
        }


    }

    public AdminResponse obterUsuario(int id) {
        try{
            UsuarioDTO usuario = api.obterUsuario(id);
            return new AdminResponse(true, "Usuario obtido com sucesso", usuario);
        } catch (Exception e){
            return new AdminResponse(false, "Erro no obter usuario" + e.getMessage(), null);
        }
    }

    public AdminResponse excluirUsuario(int id) {
        try {
            api.deletarUsuario(id);
            return new AdminResponse(true, "Usuario removido com sucesso", null);
        } catch(Exception e){
            return new AdminResponse(false, "Erro no excluir usuario" + e.getMessage(), null);
        }
    }

    public AdminResponse alterarUsuario(int id, UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioAlterado = api.atualizarUsuario(id, usuarioDTO);
            return new AdminResponse(true, "Usuario alterado com sucesso", usuarioAlterado);
        } catch (Exception e){
            return new AdminResponse(false, "Erro ao alterar usuario" + e.getMessage(), null);
        }
    }
}
