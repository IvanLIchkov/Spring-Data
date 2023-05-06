package com.example._12_springdataadvancedqueringexercise.repositories;


import com.example._12_springdataadvancedqueringexercise.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
