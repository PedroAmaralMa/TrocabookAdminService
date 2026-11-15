package com.lab.labweb.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SuporteEntity {

    private String id;
    private String nomeUsuario;
    private String emailUsuario;
    private String titulo; // <--- NOVO CAMPO
    private String mensagem;
    private String dataEnvio;

    public SuporteEntity() {
        this.dataEnvio = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public SuporteEntity(String nomeUsuario, String emailUsuario, String titulo, String mensagem) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataEnvio = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(String dataEnvio) { this.dataEnvio = dataEnvio; }
}