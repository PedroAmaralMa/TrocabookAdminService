package com.lab.labweb.controller.request;

import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;

public class AlterarUsuarioRequest {
    private UsuarioDTO usuarioDTO;
    private LogAdminDTO logAdminDTO;

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public LogAdminDTO getLogAdminDTO() {
        return logAdminDTO;
    }

    public void setLogAdminDTO(LogAdminDTO logAdminDTO) {
        this.logAdminDTO = logAdminDTO;
    }
}

