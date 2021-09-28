package com.laft.laftcrud.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.WriteResult;
import com.laft.laftcrud.database.FirebaseInitializer;
import com.laft.laftcrud.dto.PostDTO;
import com.laft.laftcrud.service.PostManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManagementServiceImpl implements PostManagementService {

    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<PostDTO> list() {

        return null;
    }

    @Override
    public Boolean add(PostDTO post) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("title", post.getTitle());
        docData.put("content", post.getContent());

        CollectionReference posts = firebase.getFirestore().collection("post");
        ApiFuture<WriteResult> wApiFuture = posts.document().create(docData);

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
    public Boolean edit(String id, PostDTO post) {

        return null;
    }

    @Override
    public Boolean delete(String id) {

        return null;
    }

}
