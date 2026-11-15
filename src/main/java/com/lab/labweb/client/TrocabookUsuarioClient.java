package com.lab.labweb.client;

import com.lab.labweb.model.DTO.AdminUsuarioInputDTO;
import com.lab.labweb.model.DTO.AdminUsuarioOutputDTO;
import com.lab.labweb.config.TrocabookFeignConfig; // <-- Mantém o 'i' conforme está na sua pasta config
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "trocabook",
        url = "${TROCABOOK_API_URL}",
        configuration = TrocabookFeignConfig.class // <-- Mantém o 'i' aqui também
)
public interface TrocabookUsuarioClient {

    @GetMapping("/api/internal/admin/usuarios")
    List<AdminUsuarioOutputDTO> buscarTodos();

    @GetMapping("/api/internal/admin/usuarios/{uid}")
    AdminUsuarioOutputDTO buscarPorUid(@PathVariable("uid") String uid);

    @PatchMapping("/api/internal/admin/usuarios/{uid}")
    AdminUsuarioOutputDTO atualizar(
            @PathVariable("uid") String uid,
            @RequestBody AdminUsuarioInputDTO input
    );

    @PostMapping("/api/internal/enviar-email")
    void enviarEmail(@RequestBody Map<String, String> payload);
}