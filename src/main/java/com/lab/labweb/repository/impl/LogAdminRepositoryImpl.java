package com.lab.labweb.repository.impl;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.lab.labweb.model.LogAdmin;
import com.lab.labweb.repository.LogAdminRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class LogAdminRepositoryImpl implements LogAdminRepository {

    private static final String COLECAO = "logs_admin";

    private final Firestore firestore;

    public LogAdminRepositoryImpl(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public LogAdmin salvar(LogAdmin logAdmin) {
        try {
            var documento = firestore
                    .collection(COLECAO)
                    .document();

            logAdmin.setId(documento.getId());

            documento
                    .set(logAdmin)
                    .get();

            return logAdmin;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Thread interrompida ao salvar log administrativo",
                    e
            );

        } catch (ExecutionException e) {
            throw new RuntimeException(
                    "Erro ao salvar log administrativo",
                    e
            );
        }
    }

    @Override
    public List<LogAdmin> listarTodosPorDataOperacaoDecrescente() {
        try {
            var documentos = firestore
                    .collection(COLECAO)
                    .orderBy(
                            "dataOperacao",
                            Query.Direction.DESCENDING
                    )
                    .get()
                    .get();

            return documentos
                    .getDocuments()
                    .stream()
                    .map(documento ->
                            documento.toObject(LogAdmin.class)
                    )
                    .toList();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Thread interrompida ao listar logs administrativos",
                    e
            );

        } catch (ExecutionException e) {
            throw new RuntimeException(
                    "Erro ao listar logs administrativos",
                    e
            );
        }
    }
}