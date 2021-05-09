package com.example.springmvcboot.web.hiber.service;

import com.example.springmvcboot.web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addUser(User user);
    User getUserById(String id);
    void deleteUserById(String id);
    void updateUser(String id, User user);
    User getUserByName(String s);
}
