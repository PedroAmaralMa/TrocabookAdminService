package com.lab.labweb.model.DTO;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para Usuário.
 *
 * <p>Essa classe é utilizada para transferir dados de usuários
 * entre o back-end, o front-end ou serviços, sem expor a entidade
 * completa do banco de dados.</p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *   <li>{@link #id} - Identificador único do usuário.</li>
 *   <li>{@link #nome} - Nome completo do usuário.</li>
 *   <li>{@link #email} - Email do usuário, único no sistema.</li>
 *   <li>{@link #senha} - Senha do usuário, usada para login (em produção deve ser criptografada).</li>
 *   <li>{@link #cpf} - CPF do usuário, usado como identificação.</li>
 *   <li>{@link #foto} - URL ou caminho da foto do usuário.</li>
 *   <li>{@link #avaliacao} - Avaliação média do usuário.</li>
 *   <li>{@link #status} - Status do usuário ('A' = Ativo, 'I' = Inativo).</li>
 * </ul>
 *
 * <p><b>Observações:</b></p>
 * <ul>
 *   <li>Classe apenas para transferência de dados, sem lógica de negócio.</li>
 *   <li>No futuro, algumas propriedades como {@link #senha} podem ser removidas
 *   do DTO exposto em APIs públicas por questões de segurança.</li>
 * </ul>
 */
public class UsuarioDTO {
    private int id;
    private double avaliacao;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
