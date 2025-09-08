package com.lab.labweb.config;

import com.lab.labweb.DTO.DashboardDTO;
import com.lab.labweb.DTO.UsuarioDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * Interface FeignClient para comunicação com a API principal.
 *
 * <p>Define os endpoints disponíveis para buscar informações de usuários e dashboard,
 * bem como operações de CRUD sobre usuários.</p>
 *
 * <p><b>Observações sobre o desenvolvimento:</b></p>
 * <ul>
 *   <li>Os métodos que recebem uma lista como parâmetro
 *       ({@code List<UsuarioDTO>})
 *       <ul>
 *           <li>{@code deletarUsuario(int id, List<UsuarioDTO> lista)}</li>
 *           <li>{@code obterUsuario(int id, List<UsuarioDTO> lista)}</li>
 *           <li>{@code atualizarUsuario(int id, UsuarioDTO usuarioDTO, List<UsuarioDTO> lista)}</li>
 *       </ul>
 *       são utilizados apenas para testes/mocks e <b>serão removidos no futuro</b>.</li>
 *   <li>Os métodos que utilizam diretamente os endpoints REST
 *       (<code>@GetMapping</code>, <code>@PutMapping</code>, <code>@DeleteMapping</code>)
 *       representam a implementação final que será mantida e utilizada na comunicação real
 *       com a API principal.</li>
 * </ul>
 */
@FeignClient(name = "principal-api", url = "http://localhost:8080")
public interface IPrincipalApiClient {

    /**
     * Obtém os dados do dashboard.
     * @return {@link DashboardDTO} com totais de usuários, livros e negociações.
     */
    @GetMapping("/dados/dashboard")
    DashboardDTO obterDashboard();

    /**
     * Lista todos os usuários cadastrados.
     * @return {@code List<UsuarioDTO>} com os usuários.
     */
    @GetMapping("/dados/listarUsuarios")
    List<UsuarioDTO> listarUsuarios();

    /**
     * Obtém um usuário pelo ID.
     * @param id ID do usuário
     * @return {@link UsuarioDTO} com os dados do usuário
     */
    @GetMapping("/dados/obterUsuario/{id}")
    UsuarioDTO obterUsuario(@PathVariable int id);

    /**
     * Exclui um usuário pelo ID.
     * @param id ID do usuário
     */
    @DeleteMapping("/dados/deletarUsuario/{id}")
    void deletarUsuario(@PathVariable int id);

    /**
     * Atualiza os dados de um usuário pelo ID.
     * @param id ID do usuário
     * @param usuarioDTO Dados atualizados do usuário
     * @return {@link UsuarioDTO} atualizado
     */
    @PutMapping("/dados/atualizarUsuario/{id}")
    UsuarioDTO atualizarUsuario(@PathVariable int id, @RequestBody UsuarioDTO usuarioDTO);

    // MÉTODOS SOMENTE PARA TESTES (SERÃO REMOVIDOS NO FUTURO)

    /**
     * Remove um usuário da lista de teste.
     * @param id ID do usuário
     * @param lista Lista de usuários de teste
     */
    void deletarUsuario(int id, List<UsuarioDTO> lista);

    /**
     * Obtém um usuário da lista de teste pelo ID.
     * @param id ID do usuário
     * @param lista Lista de usuários de teste
     * @return {@link UsuarioDTO} encontrado na lista
     */
    UsuarioDTO obterUsuario(int id, List<UsuarioDTO> lista);

    /**
     * Atualiza um usuário na lista de teste pelo ID.
     * @param id ID do usuário
     * @param usuarioDTO Dados atualizados
     * @param lista Lista de usuários de teste
     * @return {@link UsuarioDTO} atualizado
     */
    UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTO, List<UsuarioDTO> lista);
}
