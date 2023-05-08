package com.example.springdataautomapping.entities;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private boolean isAdmin;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Game> games;

    @OneToMany(targetEntity = Order.class, mappedBy = "buyer")
    private Set<Order> orders;

    public User() {
        this.games = new ArrayList<>();
        this.orders = new HashSet<>();
    }

    public User(String email, String password, String fullName, boolean isAdmin) {
        this();
        setEmail(email);
        setPassword(password);
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

    public void addGames(Game game){
        this.games.add(game);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.contains("@") && !email.contains("-")){
            throw new IllegalArgumentException("Incorrect email.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Pattern passwordPatter = Pattern.compile("^(?=[a-zA-Z0-9]{6,})([A-Z]{1,}[a-z]{1,}[0-9]{1,})");
        Matcher match = passwordPatter.matcher(password);
        if(!match.matches()){
            throw new IllegalArgumentException("Incorrect username / password");
        }
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Transactional
    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }
}
