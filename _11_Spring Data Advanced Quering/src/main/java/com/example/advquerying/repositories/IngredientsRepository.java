package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameIsStartingWith(String letter);

    List<Ingredient> findByNameIn(List<String> name);

    @Modifying
    @Query("DELETE FROM Ingredient AS i where i.name LIKE :ingredientName")
    void removeIngredientByName(@Param("ingredientName") String name);

    @Modifying
    @Query("UPDATE from Ingredient as i set i.price = i.price * 1.10")
    void updateIngredientsPrice();

    @Modifying
    @Query("UPDATE FROM Ingredient AS i set i.price = i.price + 10 where i.name IN :ingredientNames")
    void updateIngredientsByGivenNames(@Param("ingredientNames") List<String> names);
}
