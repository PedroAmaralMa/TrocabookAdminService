package com.lab.labweb.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDTO {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String foto;
    private double avaliacao;
    private char status;

}
