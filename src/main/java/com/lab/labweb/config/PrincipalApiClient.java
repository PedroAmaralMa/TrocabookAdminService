package com.lab.labweb.config;

import com.lab.labweb.DTO.DashboardDTO;
import com.lab.labweb.DTO.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação mock do {@link IPrincipalApiClient} para fins de teste e prototipagem.
 *
 * <p>Todos os métodos desta classe retornam dados simulados para que a aplicação
 * possa ser testada sem depender de uma API externa real.</p>
 *
 * <p><b>Observações importantes:</b></p>
 * <ul>
 *   <li>Todos os métodos desta classe são temporários e destinados apenas a testes.</li>
 *   <li>No futuro, esta implementação será removida e substituída pelo FeignClient
 *       {@link IPrincipalApiClient}, que fará chamadas reais à API do projeto principal.</li>
 *   <li>Os métodos fornecem dados estáticos ou manipulam listas locais, sem persistência real.</li>
 * </ul>
 *
 * <p><b>Métodos incluídos:</b></p>
 * <ul>
 *   <li>{@link #obterDashboard()} - Retorna um {@link DashboardDTO} com valores simulados.</li>
 *   <li>{@link #listarUsuarios()} - Retorna uma lista com um {@link UsuarioDTO} de teste.</li>
 *   <li>{@link #obterUsuario(int)} - Retorna um {@link UsuarioDTO} de teste com o id fornecido.</li>
 *   <li>{@link #obterUsuario(int, List)} - Retorna um usuário da lista pelo id, se existir.</li>
 *   <li>{@link #deletarUsuario(int)} - Simula a exclusão de um usuário pelo id, apenas imprime no console.</li>
 *   <li>{@link #deletarUsuario(int, List)} - Remove o usuário da lista local, se existir.</li>
 *   <li>{@link #atualizarUsuario(int, UsuarioDTO)} - Atualiza o nome do usuário fornecido e retorna o objeto modificado.</li>
 *   <li>{@link #atualizarUsuario(int, UsuarioDTO, List)} - Atualiza um usuário na lista local com os dados fornecidos.</li>
 * </ul>
 */
@Component
public class PrincipalApiClient implements IPrincipalApiClient {

    @Override
    public DashboardDTO obterDashboard() {
        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setTotalUsuarios(15L);
        dashboard.setTotalLivros(20L);
        dashboard.setTotalNegociacao(15L);
        return dashboard;
    }

    @Override
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

    @Override
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

    @Override
    public UsuarioDTO obterUsuario(int id, List<UsuarioDTO> lista){
        for (UsuarioDTO u : lista){
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void deletarUsuario(int id) {
        System.out.println("Usuário deletado: " + id);
    }

    @Override
    public void deletarUsuario(int id, List<UsuarioDTO> lista){
        lista.removeIf(u -> u.getId() == id);
    }

    @Override
    public UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        usuarioDTO.setNome(usuarioDTO.getNome() + " (atualizado)");
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTONovo, List<UsuarioDTO> lista) {
        for (int i =0; i < lista.size(); i++){
            if (lista.get(i).getId() == id) {
                usuarioDTONovo.setNome(lista.get(i).getNome());
                usuarioDTONovo.setEmail(lista.get(i).getEmail());
                usuarioDTONovo.setSenha(lista.get(i).getSenha());
                usuarioDTONovo.setCpf(lista.get(i).getCpf());
                usuarioDTONovo.setFoto(lista.get(i).getFoto());
                lista.set(i, usuarioDTONovo);
                break;
            }
        }
        return usuarioDTONovo;
    }
}
