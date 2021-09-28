package com.laft.laftcrud.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.laft.laftcrud.dto.PostUserDTO;
import com.laft.laftcrud.service.PostManagementUsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/laft")
public class UsersLAFTController {

    @Autowired
    private PostManagementUsersService usersService;

    /**
     * Add users to the firestore database || Añade usuarios a la base firestore
     * 
     * @param postUser
     * @return
     */
    @PostMapping(value = "/addUser")
    public ResponseEntity<Boolean> addUsers(@RequestBody PostUserDTO postUser) {
        return new ResponseEntity<>(usersService.addUsers(postUser), HttpStatus.OK);
    }

    /**
     * Remove a user by their ID || Elimina un usuario por su ID de registro
     * 
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}/deleteUser")
    public ResponseEntity<Boolean> deleteUsers(@PathVariable(value = "id") String id) {
        return new ResponseEntity<>(usersService.deleteUsers(id), HttpStatus.OK);
    }

    /**
     * Update the data of a registered user || Actualiza los datos de un usario
     * registrado
     * 
     * @param id
     * @param postUser
     * @return
     */
    @PutMapping(value = "/{id}/editUser")
    public ResponseEntity<Boolean> editUsers(@PathVariable(value = "id") String id, @RequestBody PostUserDTO postUser) {
        return new ResponseEntity<>(usersService.editUsers(id, postUser), HttpStatus.OK);
    }

    /**
     * 
     * @param id
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @GetMapping(value = "/{id}/filterUser")
    public ResponseEntity<PostUserDTO> filterUser(@PathVariable(value = "id") String id)
            throws ExecutionException, InterruptedException {

        return new ResponseEntity<>(usersService.filterUsers(id), HttpStatus.OK);

    }

    /**
     * List of registered users || Lista de usuarios registrados
     */
    @GetMapping(value = "/listUsers")
    public ResponseEntity<List<PostUserDTO>> listUsers() {
        return new ResponseEntity<>(usersService.listUsers(), HttpStatus.OK);
    }

}
