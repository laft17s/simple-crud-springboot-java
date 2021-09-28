package com.laft.laftcrud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.laft.laftcrud.database.FirebaseInitializer;
import com.laft.laftcrud.dto.PostUserDTO;
import com.laft.laftcrud.service.PostManagementUsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManagementUsersServiceImpl implements PostManagementUsersService {

    @Autowired
    private FirebaseInitializer firebase;

    /**
     * Add users to the firestore database || Añade usuarios a la base firestore
     * 
     * @param postUserDTO
     * @return
     */
    @Override
    public Boolean addUsers(PostUserDTO postUserDTO) {

        Map<String, Object> docData = getDocData(postUserDTO);

        ApiFuture<WriteResult> wApiFuture = getCollectionReference().document().create(docData);

        try {
            if (null != wApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }

    }

    /**
     * Remove a user by their ID || Elimina un usuario por su ID de registro
     */
    @Override
    public Boolean deleteUsers(String id) {

        ApiFuture<WriteResult> wApiFuture = getCollectionReference().document(id).delete();

        try {
            if (null != wApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }

    }

    /**
     * Update the data of a registered user || Actualiza los datos de un usario
     * registrado
     */
    @Override
    public Boolean editUsers(String id, PostUserDTO postUserDTO) {

        Map<String, Object> docData = getDocData(postUserDTO);

        ApiFuture<WriteResult> wApiFuture = getCollectionReference().document(id).set(docData);

        try {
            if (null != wApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public PostUserDTO filterUsers(String id) throws InterruptedException, ExecutionException {

        // List<PostUserDTO> rsp = new ArrayList<>();
        PostUserDTO postUsers = null;

        ApiFuture<DocumentSnapshot> qApiFuture = getCollectionReference().document(id).get();

        DocumentSnapshot doc = qApiFuture.get();

        if (doc.exists()) {
            postUsers = doc.toObject(PostUserDTO.class);
            postUsers.setId(doc.getId());
            return postUsers;
        }

        return null;

    }

    /**
     * List of registered users || Lista de usuarios registrados
     */
    @Override
    public List<PostUserDTO> listUsers() {
        List<PostUserDTO> rsp = new ArrayList<>();
        PostUserDTO postUsers;

        ApiFuture<QuerySnapshot> qApiFuture = getCollectionReference().get();
        try {
            for (DocumentSnapshot doc : qApiFuture.get().getDocuments()) {
                postUsers = doc.toObject(PostUserDTO.class);
                postUsers.setId(doc.getId());
                rsp.add(postUsers);
            }
            return rsp;
        } catch (Exception e) {

            return null;
        }

    }

    /**
     * Extract the collection instance || Extrae la instancia de la colección
     */
    private CollectionReference getCollectionReference() {
        return firebase.getFirestore().collection("laft-users");
    }

    /**
     * 
     * @param postUserDTO
     * @return
     */
    private Map<String, Object> getDocData(PostUserDTO postUserDTO) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("name", postUserDTO.getName());
        docData.put("surname", postUserDTO.getSurname());
        docData.put("nameUser", postUserDTO.getNameUser());
        docData.put("birth", postUserDTO.getBirth());
        docData.put("mail", postUserDTO.getMail());
        docData.put("password", postUserDTO.getPassword());
        docData.put("role", postUserDTO.getRole());
        return docData;
    }

}
