package com.estorm.springbootrestfulclient;

import com.estorm.springbootrestfulclient.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ClientApplication {

    private static final String GET_ALL_USERS = "http://94.198.50.185:7081/api/users";
    private static final String POST_USER = "http://94.198.50.185:7081/api/users";
    private static final String PUT_USER = "http://94.198.50.185:7081/api/users";
    private static final String DELETE_USER = "http://94.198.50.185:7081/api/users/";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static List<String> cookies;

    public static void main(String[] args) {
        ClientApplication clientApplication = new ClientApplication();
        clientApplication.getAllUsers();
        User user3 = new User(3L, "James", "Brown", 20);
        clientApplication.getNewUser(user3);
        user3.setName("Thomas");
        user3.setLastName("Shelby");
        clientApplication.getUpdateUser(user3);
        clientApplication.getDeleteUser(user3.getId());
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public void getAllUsers() {
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(GET_ALL_USERS,
                HttpMethod.GET, entity, String.class);
        cookies = response.getHeaders().get("Set-Cookie");
        for (String c : cookies) {
            System.out.println(c);
        }
        String result = response.getBody();
        System.out.println(result);
    }

    public void getNewUser(User user) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(POST_USER,
                HttpMethod.POST, entity, String.class);

        System.out.println(response.getBody());
    }

    public void getUpdateUser(User user) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(PUT_USER,
                HttpMethod.PUT, entity, String.class);

        System.out.println(response.getBody());
    }

    public void getDeleteUser(Long id) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(DELETE_USER + id,
                HttpMethod.DELETE, entity, String.class);

        System.out.println(response.getBody());

    }

}