package com.lab.labweb.repository.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.lab.labweb.model.SuporteEntity;
import com.lab.labweb.repository.SuporteRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class SuporteRepositoryImpl implements SuporteRepository {

    private static final String COLLECTION_NAME = "suporte_mensagens";

    @Override
    public void salvar(SuporteEntity suporte) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection(COLLECTION_NAME).document();
        suporte.setId(docRef.getId());
        docRef.set(suporte).get();
    }

    @Override
    public List<SuporteEntity> listarTodas() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<SuporteEntity> lista = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            SuporteEntity suporte = document.toObject(SuporteEntity.class);
            // Garante que o ID do objeto venha preenchido com o ID real do documento do Firestore
            suporte.setId(document.getId());
            lista.add(suporte);
        }
        return lista;
    }

    @Override
    public void deletar(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).document(id).delete().get();
    }
}