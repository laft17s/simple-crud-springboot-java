package com.laft.laftcrud.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.laft.laftcrud.dto.PostUserDTO;

public interface PostManagementUsersService {

    List<PostUserDTO> listUsers();

    Boolean addUsers(PostUserDTO postUserDTO);

    Boolean editUsers(String id, PostUserDTO postUserDTO);

    PostUserDTO filterUsers(String id) throws ExecutionException, InterruptedException;

    Boolean deleteUsers(String id);

}
