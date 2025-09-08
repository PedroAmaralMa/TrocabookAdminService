package com.lab.labweb.controller;

import com.lab.labweb.DTO.UsuarioDTO;
import com.lab.labweb.model.Admin;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.response.AdminResponse;
import com.lab.labweb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private IAdminService adminService;

    private AdminResponse adminResponse;

    private List<UsuarioDTO> lista;

    /**
     * Página inicial de prototipagem.
     * <p><b>Provisório:</b> futuro front-end consumirá APIs REST.</p>
     * @return nome do template Thymeleaf "index"
     */
    @GetMapping("/home")
    public String home() {
        return "index";
    }

    /**
     * Formulário de cadastro de administrador.
     * <p><b>Provisório:</b> futuro front-end terá endpoint REST.</p>
     * @param model objeto Model do Spring
     * @return nome do template Thymeleaf "cadastro"
     */
    @GetMapping("/cadastro")
    public String cadastro(Model model){
        model.addAttribute("admin", new Admin());
        return "cadastro";
    }

    /**
     * Cadastro de administrador via formulário.
     * @param admin objeto Admin preenchido no formulário
     * @return redirecionamento para login se sucesso, senão retorna "cadastro"
     */
    @PostMapping("/cadastrar")
    public String cadastrar(Admin admin){
        adminResponse = adminService.cadastro(admin.getNome(), admin.getSenha(), admin.getEmail());
        if (adminResponse.isResultado()) {
            return "redirect:/login";
        }
        return "cadastro";
    }

    /**
     * Página de login de administrador.
     * <p><b>Provisório:</b> futuro front-end terá endpoint REST.</p>
     * @return nome do template Thymeleaf "login"
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Autenticação do administrador via formulário.
     * @param email email do admin
     * @param senha senha do admin
     * @return redireciona para home se sucesso, senão retorna login
     */
    @PostMapping("/logar")
    public String logar(@RequestParam("email") String email, @RequestParam("senha") String senha){
        adminResponse = adminService.loginAdmin(email, senha);
        if (adminResponse.isResultado()) {
            return "redirect:/home";
        }
        return "login";
    }

    /**
     * Listagem de usuários para prototipagem.
     * <p><b>Provisório:</b> será substituído por API REST.</p>
     * @param model objeto Model
     * @return template "listaUsuarios" ou redireciona para home
     */
    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        adminResponse = adminService.listarUsuarios();
        lista = (List<UsuarioDTO>) adminResponse.getData();
        if (adminResponse.isResultado()) {
            model.addAttribute("usuarios", lista);
            return "listaUsuarios";
        }
        return "redirect:/home";
    }

    /**
     * Exclusão de usuário via formulário.
     * <p><b>Provisório:</b> será substituído por API REST.</p>
     * @param id id do usuário
     * @return redireciona para listaUsuarios ou home
     */
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) {
        adminResponse = adminService.excluirUsuario(id, lista);
        if (adminResponse.isResultado()) {
            System.out.println(lista.size());
            return "redirect:/listaUsuarios";
        }
        return "redirect:/home";
    }

    /**
     * Formulário para alterar dados do usuário.
     * <p><b>Provisório:</b> será substituído por API REST.</p>
     * @param id id do usuário
     * @param model objeto Model
     * @return template "alterar" ou redireciona para home
     */
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable int id, Model model) {
        adminResponse = adminService.obterUsuario(id, lista);
        if (adminResponse.isResultado()) {
            UsuarioDTO usuario = (UsuarioDTO) adminResponse.getData();
            model.addAttribute("usuario", usuario);
            return "alterar";
        }
        return "redirect:/home";
    }

    /**
     * Atualiza usuário via formulário.
     * <p><b>Provisório:</b> será substituído por API REST.</p>
     * @param id id do usuário
     * @param usuario objeto UsuarioDTO preenchido no formulário
     * @return redireciona para listaUsuarios ou home
     */
    @PostMapping("/alterar/{id}")
    public String alterar(@PathVariable int id, UsuarioDTO usuario) {
        adminResponse = adminService.alterarUsuario(id, usuario, lista);
        if (adminResponse.isResultado()) {
            return "redirect:/listaUsuarios";
        }
        return "redirect:/home";
    }

    /**
     * Visualização do dashboard.
     * <p><b>Provisório:</b> será substituído por API REST para consumo do front-end.</p>
     * @param model objeto Model
     * @return template "dashboard" ou redireciona para home
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        adminResponse = adminService.obterDashboard();
        if (adminResponse.isResultado()) {
            List<Dashboard> dashboards = (List<Dashboard>) adminResponse.getData();
            model.addAttribute("dashboards", dashboards);
            return "dashboard";
        }
        return "redirect:/home";
    }
}
