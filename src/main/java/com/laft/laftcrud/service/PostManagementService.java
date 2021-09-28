package com.laft.laftcrud.service;

import java.util.List;

import com.laft.laftcrud.dto.PostDTO;

public interface PostManagementService {

    List<PostDTO> list();

    Boolean add(PostDTO post);

    Boolean edit(String id, PostDTO post);

    Boolean delete(String id);

}
