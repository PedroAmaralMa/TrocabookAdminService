package com.lab.labweb.repository;

import com.lab.labweb.model.LogAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável por gerenciar as operações de persistência
 * da entidade {@link LogAdmin}, que registra as ações realizadas pelos administradores
 * dentro do sistema.
 *
 * <p>Esta interface estende {@link JpaRepository}, herdando todos os métodos necessários
 * para operações CRUD, paginação e ordenação, sem necessidade de implementação manual.</p>
 *
 * <p><b>Principais operações herdadas de JpaRepository:</b></p>
 * <ul>
 *     <li>{@code save(LogAdmin log)} – registra uma nova ação de administrador</li>
 *     <li>{@code findById(Long id)} – busca um log específico pelo identificador</li>
 *     <li>{@code findAll()} – lista todos os registros de logs armazenados</li>
 *     <li>{@code delete(LogAdmin log)} – remove um registro de log do banco</li>
 * </ul>
 *
 * <p>O repositório {@link LogAdminRepository} é essencial para auditoria e rastreabilidade
 * das ações administrativas, permitindo que o sistema mantenha um histórico detalhado
 * de atividades realizadas por administradores.</p>
 */
@Repository
public interface LogAdminRepository extends JpaRepository<LogAdmin, Long> {
}
