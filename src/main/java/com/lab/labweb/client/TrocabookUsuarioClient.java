package com.lab.labweb.client;

import com.lab.labweb.config.TrocabookFeignConfig;
import com.lab.labweb.model.DTO.AdminUsuarioInputDTO;
import com.lab.labweb.model.DTO.AdminUsuarioOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "trocabook",
        url = "${trocabook.api.url}",
        configuration = TrocabookFeignConfig.class
)
public interface TrocabookUsuarioClient {
    @GetMapping("/api/internal/admin/usuarios")
    List<AdminUsuarioOutputDTO> buscarTodos();

    @GetMapping("/api/internal/admin/usuarios/{uid}")
    AdminUsuarioOutputDTO buscarPorUid(
            @PathVariable("uid") String uid
    );

    @PatchMapping("/api/internal/admin/usuarios/{uid}")
    AdminUsuarioOutputDTO atualizar(
            @PathVariable("uid") String uid,
            @RequestBody AdminUsuarioInputDTO input
    );
}
