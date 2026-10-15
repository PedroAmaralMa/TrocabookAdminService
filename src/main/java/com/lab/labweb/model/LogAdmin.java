package com.lab.labweb.model;



import com.google.cloud.Timestamp;

public class LogAdmin {

    private String id;

    private Operacao operacao;

    private String uidAdmin;

    private String uidUsuario;


    private Timestamp dataOperacao;

    public LogAdmin() {
    }

    public enum Operacao {
        ALTERACAO,
        DESATIVACAO,
        REATIVACAO
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public String getUidAdmin() {
        return uidAdmin;
    }

    public void setUidAdmin(String uidAdmin) {
        this.uidAdmin = uidAdmin;
    }

    public String getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(String uidUsuario) {
        this.uidUsuario = uidUsuario;
    }

    public Timestamp getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Timestamp dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}