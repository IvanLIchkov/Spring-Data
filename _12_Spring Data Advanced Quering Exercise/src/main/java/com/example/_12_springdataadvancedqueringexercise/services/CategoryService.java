package com.example._12_springdataadvancedqueringexercise.services;

import com.example._12_springdataadvancedqueringexercise.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategory();
}
