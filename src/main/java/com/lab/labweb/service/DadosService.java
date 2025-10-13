package com.lab.labweb.service;


import com.lab.labweb.model.DTO.DashboardDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementação concreta da interface {@link IDadosService},
 * responsável por consumir uma API externa que fornece dados
 * e funcionalidades relacionadas a dashboards e usuários.
 *
 * <p>Esta classe faz uso da classe {@link RestTemplate} do Spring Framework
 * para realizar chamadas HTTP (GET, PUT e DELETE), de forma padronizada
 * e reutilizável dentro do sistema.</p>
 *
 * <p>Ao centralizar a comunicação com a API, esta implementação permite
 * desacoplar o restante da aplicação das dependências externas,
 * facilitando manutenção e testes.</p>
 *
 * <p><b>Base da API consumida:</b> {@code http://localhost:8080/dados}</p>
 */
@Service
public class DadosService implements IDadosService{
    /** Cliente HTTP utilizado para realizar as requisições REST. */
    private final RestTemplate restTemplate;
    /** URL base da API externa. */
    private static final String api = "http://localhost:8080/dados";

    /**
     * Construtor que injeta a dependência {@link RestTemplate}.
     *
     * @param restTemplate cliente HTTP configurado pelo Spring
     */
    @Autowired
    public DadosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Obtém os dados agregados da plataforma (dashboard) a partir da API externa.
     *
     * <p>Realiza uma requisição GET para o endpoint {@code /dashboard}
     * e retorna um objeto {@link DashboardDTO} com as estatísticas gerais.</p>
     *
     * @return um {@link DashboardDTO} contendo os dados do dashboard,
     *         como total de usuários, livros e negociações
     */
    public DashboardDTO obterDashboard() {
        ResponseEntity<DashboardDTO> dashboard = restTemplate.exchange(
                api + "/dashboard",
                HttpMethod.GET,
                null,
                DashboardDTO.class
                );
        return dashboard.getBody();
    }

    /**
     * Atualiza os dados de um usuário existente na API externa.
     *
     * <p>Envia uma requisição PUT para o endpoint {@code /atualizar/{id}},
     * contendo os novos dados do usuário no corpo da requisição.</p>
     *
     * @param idUsuario o identificador do usuário a ser atualizado
     * @param usuario objeto {@link UsuarioDTO} com os novos dados
     * @return o {@link UsuarioDTO} atualizado retornado pela API
     */
    public UsuarioDTO atualizarUsuario(int idUsuario, UsuarioDTO usuario) {
        HttpEntity<UsuarioDTO> entity = new HttpEntity<>(usuario);
        ResponseEntity<UsuarioDTO> usuarioAtualizado = restTemplate.exchange(
                api + "/atualizar/{id}",
                HttpMethod.PUT,
                entity,
                UsuarioDTO.class,
                idUsuario
        );
        return usuarioAtualizado.getBody();
    }

    /**
     * Exclui um usuário da base de dados através da API externa.
     *
     * <p>Realiza uma requisição DELETE para o endpoint {@code /deletar/{id}}.
     * Nenhum corpo de resposta é esperado nesta operação.</p>
     *
     * @param idUsuario o identificador do usuário a ser excluído
     */
    public void deletarUsuario(int idUsuario) {
        restTemplate.exchange(
                api + "/deletar/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                idUsuario
        );

    }


}
