package com.lab.labweb.controller.request;

import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;

/**
 * Utilizado para encapsular os dados necessários
 * para a alteração de um usuário pelo administrador.
 *
 * <p>Este objeto é enviado no corpo da requisição ao endpoint de alteração
 * de usuário, contendo:</p>
 * <ul>
 *   <li>{@link UsuarioDTO} – os novos dados do usuário a serem atualizados</li>
 *   <li>{@link LogAdminDTO} – informações do administrador responsável pela operação</li>
 * </ul>
 *
 * <p>É utilizado principalmente pelo {@link com.lab.labweb.controller.AdminController}
 * no método de alteração de usuário via API REST.</p>
 */
public class AlterarUsuarioRequest {

    /** Dados do usuário a serem atualizados. */
    private UsuarioDTO usuarioDTO;

    /** Informações do administrador que está realizando a alteração. */
    private LogAdminDTO logAdminDTO;

    /**
     * Obtém os dados do usuário.
     *
     * @return objeto {@link UsuarioDTO} com os dados do usuário
     */
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    /**
     * Define os dados do usuário.
     *
     * @param usuarioDTO objeto {@link UsuarioDTO} com os novos dados do usuário
     */
    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    /**
     * Obtém as informações do administrador responsável.
     *
     * @return objeto {@link LogAdminDTO} com os dados do administrador
     */
    public LogAdminDTO getLogAdminDTO() {
        return logAdminDTO;
    }

    /**
     * Define as informações do administrador responsável.
     *
     * @param logAdminDTO objeto {@link LogAdminDTO} com os dados do administrador
     */
    public void setLogAdminDTO(LogAdminDTO logAdminDTO) {
        this.logAdminDTO = logAdminDTO;
    }
}
