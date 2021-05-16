package com.example.springmvcboot.web.hiber.dao;

import com.example.springmvcboot.web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void addUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    void updateUser(User user);
    User getUserByName(String s);
}
