package com.example._12_springdataadvancedqueringexercise.services;

import com.example._12_springdataadvancedqueringexercise.models.Category;
import com.example._12_springdataadvancedqueringexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategory() {
        Random random = new Random();
        long size = this.categoryRepository.count();

        long categoriesCount = random.nextLong(size)+1;

        Set<Long> categoriesIds = new HashSet<>();

        for (int i = 0; i < categoriesCount; i++) {
            long nextId = random.nextLong(size)+1;

            categoriesIds.add(nextId);
        }
        List<Category> allById = this.categoryRepository.findAllById(categoriesIds);

        return new HashSet<>(allById);
    }
}
