package com.example._12_springdataadvancedqueringexercise.services;

import java.io.IOException;

public interface SeedService {
    void seedAuthors() throws IOException;

    void seedCategories() throws IOException;

    void seedBooks() throws IOException;

    default void seedAll() throws IOException {
        seedAuthors();
        seedBooks();
        seedCategories();
    }
}
