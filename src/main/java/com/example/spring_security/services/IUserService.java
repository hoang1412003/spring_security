package com.example.spring_security.services;

import com.example.spring_security.models.User;

import java.util.List;

public interface IUserService {
    User createUser(User user) throws Exception;
    List<User> getAllUsers();
    User getUserByUsername(String username);
    String login(String username, String password) throws Exception;
}
