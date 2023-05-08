package com.example.springdataautomapping.services;

import com.example.springdataautomapping.entities.User;

public interface UserService{

    void registerNewUser(User newUser);

    User findUser(String email, String password);
}
