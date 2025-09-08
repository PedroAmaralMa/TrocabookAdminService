package com.lab.labweb.repository;

import com.lab.labweb.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade {@link Dashboard}.
 *
 * Estende {@link JpaRepository} para fornecer operações CRUD
 * básicas para registros de dashboards no banco de dados.
 *
 * Esta interface permite salvar, buscar, atualizar e deletar
 * instâncias de {@link Dashboard}, além de consultas personalizadas
 * caso necessário.
 *
 * Exemplos de operações herdadas de JpaRepository:
 * <ul>
 *     <li>save(Dashboard dashboard)</li>
 *     <li>findById(Integer id)</li>
 *     <li>findAll()</li>
 *     <li>delete(Dashboard dashboard)</li>
 * </ul>
 *
 *
 *
 */
@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {

}
