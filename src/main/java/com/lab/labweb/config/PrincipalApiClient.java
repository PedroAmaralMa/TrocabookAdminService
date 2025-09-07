package com.lab.labweb.config;

import com.lab.labweb.DTO.DashboardDTO;
import com.lab.labweb.DTO.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrincipalApiClient implements IPrincipalApiClient {

    public DashboardDTO obterDashboard() {
        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setTotalUsuarios(15L);
        dashboard.setTotalLivros(20L);
        dashboard.setTotalNegociacao(15L);
        return dashboard;
    }


    public List<UsuarioDTO> listarUsuarios() {
        UsuarioDTO u = new UsuarioDTO();
        List<UsuarioDTO> usuarios = new ArrayList<>();
        u.setId(1);
        u.setNome("Usuário Teste");
        u.setEmail("teste@email.com");
        u.setSenha("123456");
        u.setCpf("123.456.789");
        u.setFoto("usuario.jpg");
        u.setAvaliacao(3);
        u.setStatus('A');
        usuarios.add(u);
        return usuarios;
    }

    public UsuarioDTO obterUsuario(int id) {
        UsuarioDTO u = new UsuarioDTO();
        u.setId(id);
        u.setNome("Usuário Teste");
        u.setEmail("teste@email.com");
        u.setSenha("123456");
        u.setCpf("123.456.789");
        u.setFoto("usuario.jpg");
        u.setAvaliacao(3);
        u.setStatus('A');
        return u;
    }

    public UsuarioDTO obterUsuario(int id, List<UsuarioDTO> lista){
        for (UsuarioDTO u : lista){
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }


    public void deletarUsuario(int id) {
        System.out.println("Usuário deletado: " + id);
    }

    public void deletarUsuario(int id, List<UsuarioDTO> lista){
        lista.removeIf(u -> u.getId() == id);
    }


    public UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        usuarioDTO.setNome(usuarioDTO.getNome() + " (atualizado)");
        return usuarioDTO;
    }

    public UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTONovo, List<UsuarioDTO> lista) {
        for (int i =0; i < lista.size(); i++){
            if (lista.get(i).getId() == id) {
                lista.set(i, usuarioDTONovo);
            }
        }
        return usuarioDTONovo;
    }
}


