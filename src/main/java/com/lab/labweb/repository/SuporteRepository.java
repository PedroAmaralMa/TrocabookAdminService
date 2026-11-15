package com.lab.labweb.repository;

import com.lab.labweb.model.SuporteEntity;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SuporteRepository {
    void salvar(SuporteEntity suporte) throws ExecutionException, InterruptedException;
    List<SuporteEntity> listarTodas() throws ExecutionException, InterruptedException;
    void deletar(String id) throws ExecutionException, InterruptedException; // <--- ADICIONADO
}