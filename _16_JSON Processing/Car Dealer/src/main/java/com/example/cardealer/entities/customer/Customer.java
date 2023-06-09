package com.example.cardealer.entities.customer;

import com.example.cardealer.entities.sale.Sale;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private boolean isYoungDriver;

    @OneToMany(mappedBy = "customer")
    private List<Sale> purchases;

    public Customer() {
        this.purchases = new ArrayList<>();
    }

    public Customer(String name, String birthDate, boolean isYoungDriver) {
        this();
        this.name = name;
        setBirthDate(birthDate);
        this.isYoungDriver = isYoungDriver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {

        this.birthDate = LocalDateTime.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
