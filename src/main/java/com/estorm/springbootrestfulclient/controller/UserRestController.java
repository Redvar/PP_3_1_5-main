package com.estorm.springbootrestfulclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.estorm.springbootrestfulclient.model.User;

import java.util.List;

@RestController
@RequestMapping(" http://94.198.50.185:7081/api/users")
public class UserRestController {
    private static final String GET_ALL_USERS = " http://94.198.50.185:7081/api/users";
    private static final String POST_USER = " http://94.198.50.185:7081/api/users";
    private static final String PUT_USER = " http://94.198.50.185:7081/api/users";
    private static final String DELETE_USER = " http://94.198.50.185:7081/api/users/{id}";
    private static RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<User> getAllUser(){
        return getAllUser();
    }
}