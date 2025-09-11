package com.lab.labweb.controller;

import com.lab.labweb.model.DTO.UsuarioDTO;
import com.lab.labweb.model.Admin;
import com.lab.labweb.response.AdminResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * Controller responsável pelas funcionalidades de administração.
 *
 * <p>Este controller expõe endpoints que atendem aos requisitos
 * da tabela de administradores, além de páginas HTML de prototipagem
 * (somente para testes).</p>
 *
 * <p><b>Requisitos cobertos:</b></p>
 * <ul>
 *   <li>Login do administrador</li>
 *   <li>Cadastro do administrador</li>
 *   <li>Visualização do dashboard com dados agregados</li>
 *   <li>Gerenciamento de usuários (listar, alterar, excluir)</li>
 * </ul>
 *
 * <p><b>Observação:</b> Todos os métodos que retornam páginas
 * Thymeleaf (HTML) são apenas para prototipagem e testes locais.
 * No produto final, o front-end deverá consumir as APIs REST.</p>
 *
 * <p><b>Métodos provisórios que podem ser removidos ou substituídos no futuro:</b></p>
 * <ul>
 *   <li>{@link #home()} - página inicial de prototipagem.</li>
 *   <li>{@link #cadastro(Model)} e {@link #cadastrar(Admin)} - cadastro do admin via formulário HTML.</li>
 *   <li>{@link #login()} e {@link #logar(String, String)} - login do admin via formulário HTML.</li>
 *   <li>{@link #listaUsuarios(Model)} - listagem de usuários apenas para prototipagem, será substituída por API REST.</li>
 *   <li>{@link #alterar(int, Model)} e {@link #alterar(int, UsuarioDTO)} - alteração de usuários via formulário HTML.</li>
 *   <li>{@link #excluir(int)} - exclusão de usuário via formulário HTML.</li>
 *   <li>{@link #dashboard(Model)} - visualização do dashboard via Thymeleaf, futuramente consumida por front-end ou API REST.</li>
 * </ul>
 *
 * <p>Para produção, recomenda-se criar endpoints RESTful separados,
 * retornando {@link AdminResponse} ou DTOs diretamente para o front-end.</p>
 */
@Controller
public class AdminController {

}
