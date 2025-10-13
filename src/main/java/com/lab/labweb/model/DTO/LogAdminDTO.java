package com.lab.labweb.model.DTO;

/**
 * DTO (Data Transfer Object) para transporte de informações sobre operações
 * realizadas por administradores no sistema.
 *
 * <p>Este DTO é utilizado principalmente para registrar logs de ações administrativas,
 * sem trazer o objeto {@link com.lab.labweb.model.Admin} completo, apenas o ID do administrador.</p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *   <li>{@code operacao} – tipo da operação realizada (ex: "EXCLUIR", "ALTERAR")</li>
 *   <li>{@code cdUsuario} – identificador do usuário afetado pela operação</li>
 *   <li>{@code adminResponsavelId} – ID do administrador responsável pela operação</li>
 * </ul>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>Utilizado em conjunto com {@link com.lab.labweb.service.AdminService#salvarOperacao}</li>
 *   <li>Permite rastrear ações administrativas de forma segura e sem expor dados sensíveis do administrador</li>
 * </ul>
 */
public class LogAdminDTO {

    /** Tipo da operação realizada pelo administrador. */
    private String operacao;

    /** Identificador do usuário afetado pela operação. */
    private int cdUsuario;

    /** ID do administrador responsável pela operação. */
    private int adminResponsavelId;

    /**
     * Obtém o tipo da operação.
     *
     * @return tipo da operação (ex: "EXCLUIR", "ALTERAR")
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * Define o tipo da operação.
     *
     * @param operacao tipo da operação (ex: "EXCLUIR", "ALTERAR")
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * Obtém o ID do usuário afetado pela operação.
     *
     * @return ID do usuário
     */
    public int getCdUsuario() {
        return cdUsuario;
    }

    /**
     * Define o ID do usuário afetado pela operação.
     *
     * @param cdUsuario ID do usuário
     */
    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    /**
     * Obtém o ID do administrador responsável pela operação.
     *
     * @return ID do administrador
     */
    public int getAdminResponsavelId() {
        return adminResponsavelId;
    }

    /**
     * Define o ID do administrador responsável pela operação.
     *
     * @param adminResponsavelId ID do administrador
     */
    public void setAdminResponsavelId(int adminResponsavelId) {
        this.adminResponsavelId = adminResponsavelId;
    }
}
