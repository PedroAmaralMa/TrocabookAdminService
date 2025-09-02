package DTO;

import lombok.Getter;
import lombok.Setter;

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
