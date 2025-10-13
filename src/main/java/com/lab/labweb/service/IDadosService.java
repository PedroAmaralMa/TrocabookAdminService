package com.lab.labweb.service;

import com.lab.labweb.model.DTO.DashboardDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;

/**
 * Interface responsável por padronizar o consumo de uma API externa
 * que fornece e manipula dados relacionados a usuários e dashboards.
 *
 * <p>Define os métodos que devem ser implementados para realizar operações
 * como obtenção de estatísticas gerais da plataforma, atualização e exclusão
 * de usuários, mantendo a integração desacoplada e testável.</p>
 *
 * <p>Esta interface é implementada por classes de serviço que efetivamente
 * fazem as requisições HTTP (GET, PUT, DELETE) para a API externa.</p>
 *
 * <p><b>Responsabilidades:</b></p>
 * <ul>
 *     <li>Fornecer os dados agregados do sistema por meio do método {@link #obterDashboard()}</li>
 *     <li>Permitir atualização de informações de usuários cadastrados via {@link #atualizarUsuario(int, UsuarioDTO)}</li>
 *     <li>Viabilizar exclusão de contas de usuários através do método {@link #deletarUsuario(int)}</li>
 * </ul>
 *
 * <p>Ao utilizar essa interface, o código cliente (como {@code AdminService})
 * pode se comunicar com a API de forma padronizada, sem depender da implementação concreta.</p>
 */
public interface IDadosService {
    /**
     * Obtém informações agregadas de uso da plataforma (número de usuários, livros, negociações etc.).
     *
     * @return um objeto {@link DashboardDTO} contendo as estatísticas da plataforma
     */
    DashboardDTO obterDashboard();
    /**
     * Atualiza os dados de um usuário específico na API externa.
     *
     * @param idUsuario o identificador do usuário a ser atualizado
     * @param usuario um objeto {@link UsuarioDTO} contendo os novos dados
     * @return o objeto {@link UsuarioDTO} atualizado retornado pela API
     */
    UsuarioDTO atualizarUsuario(int idUsuario, UsuarioDTO usuario);
    /**
     * Exclui um usuário do sistema através da API externa.
     *
     * @param idUsuario o identificador do usuário a ser removido
     */
    void deletarUsuario(int idUsuario);
}
