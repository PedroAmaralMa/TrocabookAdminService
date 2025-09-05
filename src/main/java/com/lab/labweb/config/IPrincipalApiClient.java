package com.lab.labweb.config;


import com.lab.labweb.DTO.UsuarioDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "principal-api", url = "http://localhost:8080")
public interface IPrincipalApiClient {
    @GetMapping("/dados/totalUsuarios")
    Long obterTotalUsuarios();

    @GetMapping("/dados/totalLivros")
    Long obterTotalLivros();

    @GetMapping("/dados/totalNegociacao")
    long obterTotalNegociacao();

    @GetMapping("/dados/listarUsuarios")
    List<UsuarioDTO> listarUsuarios();

    @GetMapping("/dados/obterUsuario/{id}")
    UsuarioDTO obterUsuario(@PathVariable int id);

    @DeleteMapping("/dados/deletarUsuario/{id}")
    void deletarUsuario(@PathVariable int id);

    @PutMapping("/dados/atualizarUsuario/{id}")
    UsuarioDTO atualizarUsuario(@PathVariable int id, @RequestBody UsuarioDTO usuarioDTO);

    void deletarUsuario(int id, List<UsuarioDTO> lista);

    UsuarioDTO obterUsuario(int id, List<UsuarioDTO> lista);

    UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTO, List<UsuarioDTO> lista);
}