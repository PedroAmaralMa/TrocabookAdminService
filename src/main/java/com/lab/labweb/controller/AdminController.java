package com.lab.labweb.controller;

import com.lab.labweb.model.DTO.AdminUsuarioInputDTO;
import com.lab.labweb.model.DTO.AdminUsuarioOutputDTO;
import com.lab.labweb.service.IAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<AdminUsuarioOutputDTO>> buscarTodosUsuarios() {

        return ResponseEntity.ok(
                adminService.buscarTodosUsuarios()
        );
    }

    @GetMapping("/usuarios/{uid}")
    public ResponseEntity<AdminUsuarioOutputDTO> buscarUsuarioPorUid(
            @PathVariable String uid
    ) {

        AdminUsuarioOutputDTO usuario =
                adminService.buscarUsuarioPorUid(uid);

        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/usuarios/{uid}")
    public ResponseEntity<AdminUsuarioOutputDTO> atualizarUsuario(
            @PathVariable String uid,
            @RequestBody AdminUsuarioInputDTO input,
            Authentication authentication
    ) {

        String uidAdmin = authentication.getName();

        AdminUsuarioOutputDTO usuario =
                adminService.atualizarUsuario(
                        uid,
                        input,
                        uidAdmin
                );

        return ResponseEntity.ok(usuario);
    }
}