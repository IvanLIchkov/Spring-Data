package com.example.springdataautomapping.services;

import com.example.springdataautomapping.entities.User;
import com.example.springdataautomapping.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerNewUser(User newUser) {
        userRepository.save(newUser);
    }

    @Override
    public User findUser(String email, String password) {
        return userRepository.findUserByEmailLikeAndPasswordLike(email,password);
    }
}
