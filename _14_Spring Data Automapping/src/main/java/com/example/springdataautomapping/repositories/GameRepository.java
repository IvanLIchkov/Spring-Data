package com.example.springdataautomapping.repositories;

import com.example.springdataautomapping.dto.GameDto;
import com.example.springdataautomapping.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Modifying
    @Query("UPDATE Game g " +
            "set g.price = :price, g.size = :size"+
            " where g.id = :id ")
    int updateGame(@Param("id") int id, @Param("price")BigDecimal price, @Param("size") double size);

    @Modifying
    int deleteGameByIdEquals(int id);

    Game findGameByTitle(String title);
}
