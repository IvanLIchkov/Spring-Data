package com.example.jsonprocessing.productShop.entities.users;

import com.product_shop.entities.products.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Basic
    private Integer age;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    private List<Product> itemsBought;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    private List<Product> sellingItems;

    @ManyToMany
    private Set<User> friends;

    public User() {
        this.itemsBought = new ArrayList<>();
        this.sellingItems = new ArrayList<>();
        this.friends = new HashSet<>();
    }

    public User(String firstName, String lastName, int age) {
        this();
        this.firstName = firstName;
        setLastName(lastName);
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.length()<3){
            throw new IllegalArgumentException("Last name must be at least 3 characters long.");
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Product> getItemsBought() {
        return itemsBought;
    }

    public void buyProduct(Product productToBuy) {
        this.itemsBought.add(productToBuy);
    }

    public List<Product> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<Product> selled) {
        this.sellingItems = selled;
    }
}
