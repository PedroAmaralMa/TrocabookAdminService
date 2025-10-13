package com.lab.labweb.service;

import com.lab.labweb.model.Admin;
import com.lab.labweb.model.DTO.AdminDTO;
import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import com.lab.labweb.model.LogAdmin;
import com.lab.labweb.response.AdminResponse;

/**
 * Interface que define os serviços disponíveis para administradores.
 *
 * <p><b>Atende à tabela de requisitos:</b></p>
 * <ul>
 *   <li>{@link #cadastro(AdminDTO)} → requisito: "Administrador pode se cadastrar"</li>
 *   <li>{@link #loginAdmin(String, String)} → requisito: "Administrador pode logar"</li>
 *   <li>{@link #obterDashboard()} → requisito: "Administrador visualiza dados agregados da plataforma"</li>
 *   <li>{@link #excluirUsuario(LogAdminDTO)} → requisito: "Administrador pode excluir usuários"</li>
 *   <li>{@link #alterarUsuario(LogAdminDTO, int, UsuarioDTO)} → requisito: "Administrador pode alterar dados de usuários"</li>
 *   <li>{@link #obterAdminPorId(int)} → requisito: "Administrador pode consultar seus próprios dados"</li>
 *   <li>{@link #gerarExcel()} → requisito: "Administrador pode exportar dados da plataforma em Excel"</li>
 * </ul>
 */
public interface IAdminService {

    /**
     * Realiza o cadastro de um novo administrador na plataforma.
     *
     * @param adminDTO objeto contendo as informações do administrador (nome, email, senha etc.)
     * @return {@link AdminResponse} indicando sucesso ou falha no cadastro
     */
    AdminResponse cadastro(AdminDTO adminDTO);

    /**
     * Autentica o administrador com base no email e senha fornecidos.
     *
     * @param email email do administrador
     * @param senha senha do administrador
     * @return {@link AdminResponse} com o status da autenticação e dados do administrador, se válido
     */
    AdminResponse loginAdmin(String email, String senha);

    /**
     * Obtém informações consolidadas e métricas gerais da plataforma para exibição no dashboard administrativo.
     *
     * @return {@link AdminResponse} contendo estatísticas e dados agregados da plataforma
     */
    AdminResponse obterDashboard();

    /**
     * Exclui um usuário específico do sistema e registra o log da ação do administrador responsável.
     *
     * @param logAdmin objeto contendo os dados do log da exclusão e identificação do administrador
     * @return {@link AdminResponse} indicando sucesso ou falha na exclusão
     */
    AdminResponse excluirUsuario(LogAdminDTO logAdmin);

    /**
     * Altera os dados de um usuário existente no sistema, registrando a operação no log administrativo.
     *
     * @param logadmin objeto contendo as informações do log e do administrador que realizou a alteração
     * @param idUsuario identificador único do usuário que será alterado
     * @param usuarioDTO objeto com os novos dados do usuário
     * @return {@link AdminResponse} indicando sucesso ou falha na atualização
     */
    AdminResponse alterarUsuario(LogAdminDTO logadmin, int idUsuario, UsuarioDTO usuarioDTO);

    /**
     * Busca e retorna um administrador específico com base em seu identificador.
     *
     * @param id identificador único do administrador
     * @return objeto {@link Admin} correspondente ao ID fornecido, ou {@code null} se não encontrado
     */
    Admin obterAdminPorId(int id);

    /**
     * Gera um arquivo Excel contendo os dados administrativos ou estatísticos da plataforma.
     *
     * @return um array de bytes representando o arquivo Excel gerado
     * @throws Exception caso ocorra erro durante a geração do arquivo
     */
    byte[] gerarExcel() throws Exception;
}
