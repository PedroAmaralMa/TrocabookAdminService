package com.lab.labweb.model.DTO;

public record SuporteInputDTO(
        String nomeUsuario,
        String emailUsuario,
        String titulo,     // <--- ADICIONE ESTA LINHA
        String mensagem
) {}