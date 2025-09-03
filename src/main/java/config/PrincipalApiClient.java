package config;

import DTO.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrincipalApiClient implements IPrincipalApiClient {
    public Long obterTotalUsuarios() {
        return 15L;
    }


    public Long obterTotalLivros() {
        return 20L;
    }


    public long obterTotalNegociacao() {
        return 15L;
    }


    public List<UsuarioDTO> listarUsuarios() {
        UsuarioDTO u = new UsuarioDTO();
        u.setId(1);
        u.setNome("Usuário Teste");
        u.setEmail("teste@email.com");
        u.setSenha("123456");
        u.setCpf("123.456.789");
        u.setFoto("usuario.jpg");
        u.setAvaliacao(3);
        u.setStatus('A');
        return List.of(u);
    }

    public UsuarioDTO obterUsuario(int id) {
        UsuarioDTO u = new UsuarioDTO();
        u.setId(1);
        u.setNome("Usuário Teste");
        u.setEmail("teste@email.com");
        u.setSenha("123456");
        u.setCpf("123.456.789");
        u.setFoto("usuario.jpg");
        u.setAvaliacao(3);
        u.setStatus('A');
        return u;
    }


    public void deletarUsuario(int id) {
        System.out.println("Usuário deletado: " + id);
    }


    public UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        usuarioDTO.setNome(usuarioDTO.getNome() + " (atualizado)");
        return usuarioDTO;
    }
}


