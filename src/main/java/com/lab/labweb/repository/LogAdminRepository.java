package com.lab.labweb.repository;

import com.lab.labweb.model.LogAdmin;

import java.util.List;

public interface LogAdminRepository {

    LogAdmin salvar(LogAdmin logAdmin);

    List<LogAdmin> listarTodosPorDataOperacaoDecrescente();
}