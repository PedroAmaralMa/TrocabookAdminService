package com.lab.labweb.service;

import com.lab.labweb.model.DTO.AdminUsuarioInputDTO;
import com.lab.labweb.model.DTO.AdminUsuarioOutputDTO;

import java.util.List;

public interface IAdminService {

    List<AdminUsuarioOutputDTO> buscarTodosUsuarios();

    AdminUsuarioOutputDTO buscarUsuarioPorUid(String uid);

    AdminUsuarioOutputDTO atualizarUsuario(
            String uid,
            AdminUsuarioInputDTO input,
            String uidAdmin
    );
}