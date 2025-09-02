package config;


import DTO.UsuarioDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@Component
@FeignClient(name = "principal-api", url = "http://localhost:8080/dados")
public interface PrincipalApiClient {
    @GetMapping("/dados/totalUsuarios")
    Long obterTotalUsuarios();

    @GetMapping("/dados/totalLivros")
    Long obterTotalLivros();

    @GetMapping("/dados/totalNegociacao")
    long obterTotalNegociacao();

    @GetMapping("/dados/listarUsuarios")
    List<UsuarioDTO> listarUsuarios();

    @DeleteMapping("/dados/deletarUsuario/{id}")
    void deletarUsuario(@PathVariable int id);

    @PutMapping("/dados/atualizarUsuario/{id}")
    UsuarioDTO atualizarUsuario(@PathVariable int id, @RequestBody UsuarioDTO usuarioDTO);
}