package com.lab.labweb.model.DTO;

public record AdminUsuarioOutputDTO(
        String id,
        String nome,
        String emailPrincipal,
        String fotoPerfil,
        String status,
        double avaliacao,
        String role
) {
}