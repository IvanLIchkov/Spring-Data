package com.usersystem.usersystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 4, max = 30)
    private String username;

    @Size(min = 6, max = 50)
    private String password;
}
