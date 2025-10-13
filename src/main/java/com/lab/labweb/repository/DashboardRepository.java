package com.lab.labweb.repository;

import com.lab.labweb.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável por gerenciar as operações de persistência
 * da entidade {@link Dashboard} no banco de dados.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo
 * um conjunto completo de métodos prontos para operações CRUD,
 * paginação e ordenação, sem necessidade de implementação manual.</p>
 *
 * <p><b>Principais operações herdadas de JpaRepository:</b></p>
 * <ul>
 *     <li>{@code save(Dashboard dashboard)} – salva ou atualiza um registro de dashboard</li>
 *     <li>{@code findById(Integer id)} – busca um dashboard pelo identificador</li>
 *     <li>{@code findAll()} – retorna todos os dashboards cadastrados</li>
 *     <li>{@code delete(Dashboard dashboard)} – remove um dashboard do banco</li>
 * </ul>
 *
 * <p>Além das operações padrão, esta interface define consultas
 * personalizadas baseadas em convenções de nomenclatura do Spring Data JPA.</p>
 *
 * <p>O repositório {@link DashboardRepository} é utilizado principalmente
 * para alimentar a área administrativa do sistema, permitindo a visualização
 * de métricas e informações agregadas ordenadas por data.</p>
 */
@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    /**
     * Retorna todos os registros de {@link Dashboard} ordenados pela data de criação
     * em ordem decrescente.
     *
     * <p>Esse método é usado, por exemplo, na exibição de relatórios recentes
     * ou na geração da dashboard do administrador, garantindo que os dados
     * mais novos apareçam primeiro.</p>
     *
     * @return uma lista de objetos {@link Dashboard} ordenados pela data de criação (mais recentes primeiro)
     */
    List<Dashboard> findAllByOrderByDataCriacaoDesc();
}
