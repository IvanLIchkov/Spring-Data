package com.example.cardealer.entities.sale;

import com.example.cardealer.entities.car.Car;
import com.example.cardealer.entities.customer.Customer;
import jakarta.persistence.*;

@Entity
@Table
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double discount;

    @OneToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    public Sale() {
    }

    public Sale(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
    }

    public Sale(double discount, Car car, Customer customer) {
        this();
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double isDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
