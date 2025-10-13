package com.lab.labweb.repository;

import com.lab.labweb.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável por gerenciar as operações de persistência
 * da entidade {@link Admin} no banco de dados.
 *
 * <p>Esta interface herda de {@link JpaRepository}, o que permite o uso
 * de métodos prontos para CRUD e paginação sem necessidade de implementação manual.</p>
 *
 * <p><b>Operações padrão herdadas de JpaRepository incluem:</b></p>
 * <ul>
 *     <li>{@code save(Admin admin)} – salva ou atualiza um administrador</li>
 *     <li>{@code findById(Integer id)} – busca um administrador pelo ID</li>
 *     <li>{@code findAll()} – retorna todos os administradores cadastrados</li>
 *     <li>{@code delete(Admin admin)} – remove um administrador do banco</li>
 * </ul>
 *
 * <p>Além das operações padrão, esta interface define métodos de consulta
 * personalizados com base na convenção de nomes do Spring Data JPA.</p>
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * Busca um administrador com base no email e senha fornecidos.
     *
     * <p>Este método é utilizado durante o processo de login
     * para verificar se as credenciais correspondem a um administrador existente.</p>
     *
     * @param email o email cadastrado do administrador
     * @param senha a senha correspondente ao administrador
     * @return o objeto {@link Admin} correspondente, ou {@code null} caso não seja encontrado
     */
    Admin findByEmailAndSenha(String email, String senha);
}
