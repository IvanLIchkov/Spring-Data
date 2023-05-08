package com.example.springdataautomapping.handlers;

import com.example.springdataautomapping.entities.Game;
import com.example.springdataautomapping.entities.User;
import com.example.springdataautomapping.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserHandler {
    private static boolean isFirst = true;
    private final UserServiceImp userServiceImp;
    private static User logedUser = null;

    @Autowired
    public UserHandler(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Transactional
    public void registration(String[] data){
        String email = data[1];
        String password = data[2];
        String confirmPassword = data[3];
        if(!password.equals(confirmPassword)){
            throw new IllegalArgumentException("Passwords doesn't match!");
        }
        String fullName = data[4];
        User newUser;
        if(isFirst){
            newUser = new User(email,password,fullName,true);
            isFirst = false;
        }else{
            newUser = new User(email,password,fullName,false);
        }
        userServiceImp.registerNewUser(newUser);
        System.out.println(fullName+" was registered");
    }
    @Transactional
    public User login(String[] data){
        String email = data[1];
        String password = data[2];

        User userToLog = userServiceImp.findUser(email, password);

        if(userToLog ==null){
            throw new IllegalArgumentException("User doesn't exist!");

        }
        logedUser = userToLog;
        System.out.printf("Successfully logged in %s%n",logedUser.getFullName());
        return logedUser;
    }


    public void logOut() {
        if(logedUser == null){
            throw new IllegalArgumentException("Cannot log out. No user was logged in.");
        }
        System.out.printf("User %s successfully logged out%n",logedUser.getFullName());

        logedUser = null;
    }
}
