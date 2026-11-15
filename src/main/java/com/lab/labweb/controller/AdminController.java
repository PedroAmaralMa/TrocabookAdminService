package com.lab.labweb.controller;

import com.lab.labweb.client.TrocabookUsuarioClient;
import com.lab.labweb.model.DTO.AdminUsuarioInputDTO;
import com.lab.labweb.model.DTO.AdminUsuarioOutputDTO;
import com.lab.labweb.model.DTO.SuporteInputDTO;
import com.lab.labweb.model.SuporteEntity;
import com.lab.labweb.repository.SuporteRepository;
import com.lab.labweb.service.IAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final IAdminService adminService;
    private final SuporteRepository suporteRepository;
    private final TrocabookUsuarioClient trocabookUsuarioClient;

    public AdminController(IAdminService adminService, SuporteRepository suporteRepository, TrocabookUsuarioClient trocabookUsuarioClient) {
        this.adminService = adminService;
        this.suporteRepository = suporteRepository;
        this.trocabookUsuarioClient = trocabookUsuarioClient;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<AdminUsuarioOutputDTO>> buscarTodosUsuarios() {
        return ResponseEntity.ok(adminService.buscarTodosUsuarios());
    }

    @GetMapping("/usuarios/{uid}")
    public ResponseEntity<AdminUsuarioOutputDTO> buscarUsuarioPorUid(@PathVariable String uid) {
        AdminUsuarioOutputDTO usuario = adminService.buscarUsuarioPorUid(uid);
        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/usuarios/{uid}")
    public ResponseEntity<AdminUsuarioOutputDTO> atualizarUsuario(
            @PathVariable String uid,
            @RequestBody AdminUsuarioInputDTO input,
            Authentication authentication
    ) {
        String uidAdmin = authentication.getName();
        AdminUsuarioOutputDTO usuario = adminService.atualizarUsuario(uid, input, uidAdmin);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/suporte")
    public ResponseEntity<?> receberDuvidaSuporte(@RequestBody SuporteInputDTO input) {
        try {
            SuporteEntity entidade = new SuporteEntity(
                    input.nomeUsuario(),
                    input.emailUsuario(),
                    input.titulo(),
                    input.mensagem()
            );
            suporteRepository.salvar(entidade);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao salvar no Firestore: " + e.getMessage());
        }
    }

    @GetMapping("/suporte")
    public ResponseEntity<List<SuporteEntity>> listarDuvidasSuporte() {
        try {
            List<SuporteEntity> lista = suporteRepository.listarTodas();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/suporte/{id}")
    public ResponseEntity<?> deletarDuvidaSuporte(@PathVariable String id) {
        try {
            suporteRepository.deletar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao deletar: " + e.getMessage());
        }
    }

    @PostMapping("/suporte/{id}/responder")
    public ResponseEntity<?> responderDuvidaSuporte(
            @PathVariable String id,
            @RequestBody Map<String, String> payload
    ) {
        try {
            String emailDestino = payload.get("email");
            String resposta = payload.get("resposta");
            String tituloDuvida = payload.get("titulo");

            String htmlContent = "<h3>Olá!</h3>" +
                    "<p>Sua dúvida sobre <b>" + (tituloDuvida != null ? tituloDuvida : "Suporte Trocabook") + "</b> foi respondida pela nossa equipe:</p>" +
                    "<p style='background: #f4f4f4; padding: 15px; border-radius: 5px;'>" + resposta.replace("\n", "<br>") + "</p>" +
                    "<p>Atenciosamente,<br><b>Equipe Trocabook</b></p>";

            Map<String, String> emailPayload = Map.of(
                    "destinatario", emailDestino,
                    "assunto", "Resposta ao seu suporte: " + (tituloDuvida != null ? tituloDuvida : "Trocabook"),
                    "htmlContent", htmlContent
            );

            // Usa o client existente para enviar o e-mail pelo projeto web
            trocabookUsuarioClient.enviarEmail(emailPayload);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao enviar resposta por e-mail: " + e.getMessage());
        }
    }
}