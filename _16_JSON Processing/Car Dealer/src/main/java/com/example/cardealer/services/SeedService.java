package com.example.cardealer.services;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface SeedService {

    void seedCars() throws FileNotFoundException;

    void seedCustomers() throws FileNotFoundException;

    void seedParts() throws FileNotFoundException;

    void seedSuppliers() throws FileNotFoundException;

    void seedSales();

    default void seedAll() throws FileNotFoundException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }
}
