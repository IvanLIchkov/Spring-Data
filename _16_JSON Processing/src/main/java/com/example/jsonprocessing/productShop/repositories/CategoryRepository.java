package com.example.jsonprocessing.productShop.repositories;

import com.example.jsonprocessing.productShop.entities.categories.Category;
import com.example.jsonprocessing.productShop.entities.categories.CategoryByProductsCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
