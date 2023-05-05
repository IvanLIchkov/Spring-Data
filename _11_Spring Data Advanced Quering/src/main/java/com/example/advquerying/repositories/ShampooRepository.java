package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findAllByBrandAndSize(String brand, com.example.advquerying.entities.Size size);

    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findBySizeOrLabel_IdOrderByPrice(Size size, Long id);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findByPriceIsLessThan(BigDecimal price);

    @Query("SELECT s from Shampoo s JOIN s.ingredients as i WHERE i.name IN :ingredientNames")
    Set<Shampoo> findByIngredientsName(@Param("ingredientNames") Set<String> ingredients);

    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size< :ingredientsCount")
    Set<Shampoo> getShampooByIngCount(@Param("ingredientsCount") int count);
}
