package com.lab.labweb.service;

import com.google.cloud.Timestamp;
import com.lab.labweb.client.TrocabookUsuarioClient;
import com.lab.labweb.model.DTO.AdminUsuarioInputDTO;
import com.lab.labweb.model.DTO.AdminUsuarioOutputDTO;
import com.lab.labweb.model.LogAdmin;
import com.lab.labweb.repository.LogAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService implements IAdminService {

    private final TrocabookUsuarioClient trocabookUsuarioClient;
    private final LogAdminRepository logAdminRepository;

    public AdminService(
            TrocabookUsuarioClient trocabookUsuarioClient,
            LogAdminRepository logAdminRepository
    ) {
        this.trocabookUsuarioClient = trocabookUsuarioClient;
        this.logAdminRepository = logAdminRepository;
    }

    @Override
    public List<AdminUsuarioOutputDTO> buscarTodosUsuarios() {
        return trocabookUsuarioClient.buscarTodos();
    }

    @Override
    public AdminUsuarioOutputDTO buscarUsuarioPorUid(String uid) {
        return trocabookUsuarioClient.buscarPorUid(uid);
    }

    @Override
    public AdminUsuarioOutputDTO atualizarUsuario(
            String uid,
            AdminUsuarioInputDTO input,
            String uidAdmin
    ) {
        AdminUsuarioOutputDTO usuarioAnterior =
                trocabookUsuarioClient.buscarPorUid(uid);

        AdminUsuarioOutputDTO usuarioAtualizado =
                trocabookUsuarioClient.atualizar(uid, input);

        LogAdmin.Operacao operacao =
                determinarOperacao(usuarioAnterior, usuarioAtualizado);

        salvarOperacao(
                operacao,
                uidAdmin,
                uid
        );

        return usuarioAtualizado;
    }

    private LogAdmin.Operacao determinarOperacao(
            AdminUsuarioOutputDTO anterior,
            AdminUsuarioOutputDTO atualizado
    ) {
        if (!Objects.equals(
                anterior.status(),
                atualizado.status()
        )) {

            if ("INATIVO".equals(atualizado.status())) {
                return LogAdmin.Operacao.DESATIVACAO;
            }

            if ("ATIVO".equals(atualizado.status())) {
                return LogAdmin.Operacao.REATIVACAO;
            }
        }

        return LogAdmin.Operacao.ALTERACAO;
    }

    private void salvarOperacao(
            LogAdmin.Operacao operacao,
            String uidAdmin,
            String uidUsuario
    ) {
        LogAdmin log = new LogAdmin();

        log.setOperacao(operacao);
        log.setUidAdmin(uidAdmin);
        log.setUidUsuario(uidUsuario);
        log.setDataOperacao(Timestamp.now());

        logAdminRepository.salvar(log);
    }
}