package com.lab.labweb.repository;

import com.lab.labweb.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade {@link Admin}.
 *
 * Estende {@link JpaRepository} para fornecer operações CRUD
 * básicas e métodos de busca personalizados.
 *
 * Exemplos de operações herdadas de JpaRepository:
 * <ul>
 *     <li>save(Admin admin)</li>
 *     <li>findById(Integer id)</li>
 *     <li>findAll()</li>
 *     <li>delete(Admin admin)</li>
 * </ul>
 *
 * Também define métodos personalizados para autenticação e busca de Admins.
 *
 *
 *
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * Busca um administrador pelo email e senha.
     *
     * Usado para realizar login de administradores no sistema.
     *
     * @param email O email do administrador
     * @param senha A senha do administrador
     * @return O objeto Admin caso exista, ou null caso não seja encontrado
     */
    Admin findByEmailAndSenha(String email, String senha);
}
