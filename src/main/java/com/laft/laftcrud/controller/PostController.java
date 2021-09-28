package com.laft.laftcrud.controller;

import com.laft.laftcrud.dto.PostDTO;
import com.laft.laftcrud.service.PostManagementService;

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
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostManagementService service;

    @GetMapping(value = "/great/{name}")
    public String great(@PathVariable(value = "name") String name) {
        return "Hello " + name;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<String> list() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Boolean> add(@RequestBody PostDTO post) {
        return new ResponseEntity<Boolean>(service.add(post), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity<String> edit(@PathVariable(value = "id") String id, @RequestBody PostDTO post) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

/**
 * ********************************************************************************
 * ********************************************************************************
 * 
 * DTO: Data Transfer Object 
 * DAO: Data Access Object
 * 
 */
