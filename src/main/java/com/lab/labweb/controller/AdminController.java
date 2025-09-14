package com.lab.labweb.controller;

import com.lab.labweb.controller.request.AlterarUsuarioRequest;
import com.lab.labweb.controller.request.LoginRequest;
import com.lab.labweb.model.DTO.AdminDTO;
import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import com.lab.labweb.model.Admin;
import com.lab.labweb.repository.AdminRepository;
import com.lab.labweb.response.AdminResponse;
import com.lab.labweb.service.IAdminService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

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
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    final IAdminService adminService;

    private AdminResponse adminResponse;

    @Autowired
    AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarAdmin(@RequestBody AdminDTO adminDTO) {
        adminResponse = adminService.cadastro(adminDTO);
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(adminResponse.getData());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        adminResponse = adminService.loginAdmin(loginRequest.getEmail(), loginRequest.getSenha());
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(adminResponse.getData());
        }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Object> dashboard() {
        adminResponse = adminService.obterDashboard();
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(adminResponse.getData());
        }
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Object> excluir(@RequestBody LogAdminDTO logAdminDTO) {
        adminResponse = adminService.excluirUsuario(logAdminDTO);
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(adminResponse.getData());
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Object> alterar(@PathVariable int id, @RequestBody AlterarUsuarioRequest alterarUsuarioRequest) {
        adminResponse = adminService.alterarUsuario(alterarUsuarioRequest.getLogAdminDTO(), id, alterarUsuarioRequest.getUsuarioDTO());
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(adminResponse.getData());
        }
    }






}
