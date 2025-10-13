package com.lab.labweb.controller;

import com.lab.labweb.controller.request.AlterarUsuarioRequest;
import com.lab.labweb.controller.request.LoginRequest;
import com.lab.labweb.model.DTO.AdminDTO;
import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import com.lab.labweb.response.AdminResponse;
import com.lab.labweb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller responsável pelos endpoints administrativos do sistema.
 *
 * <p>Esta classe expõe funcionalidades de administração via API REST,
 * permitindo o cadastro e login de administradores, visualização do dashboard,
 * bem como gerenciamento de usuários (alterar, excluir).</p>
 *
 * <p>Todos os endpoints REST estão mapeados sob {@code /api/admin}.</p>
 *
 * <p><b>Responsabilidades principais:</b></p>
 * <ul>
 *   <li>Cadastro de administradores</li>
 *   <li>Login de administradores</li>
 *   <li>Obtenção de dashboard com dados agregados da plataforma</li>
 *   <li>Gerenciamento de usuários (alterar e excluir)</li>
 *   <li>Exportação de dados do dashboard em arquivo Excel</li>
 * </ul>
 *
 * <p><b>Observação:</b> Métodos que retornam páginas Thymeleaf ou HTML
 * (prototipagem) não fazem parte da versão final da API REST e podem ser removidos
 * ou substituídos por chamadas diretas do front-end.</p>
 *
 * <p>Para produção, recomenda-se que o front-end consuma diretamente os endpoints REST
 * retornando {@link AdminResponse} ou DTOs, garantindo desacoplamento e consistência.</p>
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    /** Serviço que encapsula a lógica administrativa do sistema. */
    final IAdminService adminService;

    /** Objeto utilizado para armazenar respostas temporárias do serviço. */
    private AdminResponse adminResponse;

    /**
     * Construtor que injeta a dependência {@link IAdminService}.
     *
     * @param adminService serviço responsável pelas operações administrativas
     */
    @Autowired
    AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Endpoint para cadastro de um novo administrador.
     *
     * @param adminDTO dados do administrador a ser cadastrado
     * @return {@link ResponseEntity} com status HTTP e corpo contendo
     *         mensagem de erro ou os dados do administrador cadastrado
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarAdmin(@RequestBody AdminDTO adminDTO) {
        adminResponse = adminService.cadastro(adminDTO);
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(adminResponse.getData());
        }
    }

    /**
     * Endpoint para login de administrador.
     *
     * @param loginRequest DTO contendo email e senha do administrador
     * @return {@link ResponseEntity} com status HTTP e corpo contendo
     *         mensagem de erro ou os dados do administrador logado
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        adminResponse = adminService.loginAdmin(loginRequest.getEmail(), loginRequest.getSenha());
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(adminResponse.getData());
        }
    }

    /**
     * Endpoint para obtenção do dashboard da plataforma.
     *
     * @return {@link ResponseEntity} com status HTTP e corpo contendo
     *         mensagem de erro ou a lista de dashboards
     */
    @GetMapping("/dashboard")
    public ResponseEntity<Object> dashboard() {
        adminResponse = adminService.obterDashboard();
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(adminResponse.getData());
        }
    }

    /**
     * Endpoint para exclusão de usuário.
     *
     * @param logAdminDTO DTO contendo informações do administrador responsável
     *                    e do usuário a ser excluído
     * @return {@link ResponseEntity} com status HTTP e corpo contendo
     *         mensagem de sucesso ou erro
     */
    @PostMapping("/excluir")
    public ResponseEntity<Map<String,String>> excluir(@RequestBody LogAdminDTO logAdminDTO) {
        adminResponse = adminService.excluirUsuario(logAdminDTO);
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", adminResponse.getMensagem()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Usuário excluido com sucesso!"));
        }
    }

    /**
     * Endpoint para alteração de dados de um usuário existente.
     *
     * @param id identificador do usuário a ser alterado
     * @param alterarUsuarioRequest DTO contendo os dados a serem atualizados
     *                              e informações do administrador responsável
     * @return {@link ResponseEntity} com status HTTP e corpo contendo
     *         mensagem de sucesso ou erro, ou os dados atualizados do usuário
     */
    @PutMapping("/alterar/{id}")
    public ResponseEntity<Object> alterar(@PathVariable int id, @RequestBody AlterarUsuarioRequest alterarUsuarioRequest) {
        adminResponse = adminService.alterarUsuario(alterarUsuarioRequest.getLogAdminDTO(), id, alterarUsuarioRequest.getUsuarioDTO());
        if (!adminResponse.isResultado()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(adminResponse.getMensagem());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(adminResponse.getData());
        }
    }

    /**
     * Endpoint para download do dashboard em formato Excel.
     *
     * @return {@link ResponseEntity} com status HTTP e corpo contendo
     *         os bytes do arquivo Excel, ou {@code null} em caso de erro
     */
    @GetMapping("/excel")
    public ResponseEntity<byte[]> downloadExcelDashboard() {
        try {
            byte[] excelBytes = adminService.gerarExcel();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dashboard.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
